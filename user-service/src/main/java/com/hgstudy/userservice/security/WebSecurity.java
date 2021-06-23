package com.hgstudy.userservice.security;


import com.hgstudy.userservice.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Environment env; //jwt 토큰 유효 시간 등 yaml에서 가져오려고

    public WebSecurity(Environment env,UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder)    {
        this.env = env;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    //권한 관련
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //http.authorizeRequests().antMatchers("/user/**").permitAll();
        http.authorizeRequests().antMatchers("/**")
                                .hasIpAddress("127.0.0.1") //해당 ip만 통과
                                .and()
                                .addFilter(getAuthenticationFilter());


        http.headers().frameOptions().disable(); // h2-console 같은 html이 frame으로 구성되어 있는 화면을 사용 가능하도록 disalbe()
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.setAuthenticationManager(authenticationManager()); //스프링 시큐리티에서 기본으로 제공하는 매니저 사용

        return authenticationFilter;
    }

    //select pwd from users where email = ?
    //db_pwd(encrypted) == input_pwd(encrypted)
    //인증 관련
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }
}
