package com.hgstudy.userservice.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hgstudy.userservice.service.UserService;
import com.hgstudy.userservice.vo.RequestLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    private UserService userService;
//    private Environment env;
//
//    public AuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, Environment env) {
//        super(authenticationManager);
//        this.userService = userService;
//        this.env = env;
//    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            //  "/login"시 1번째로 탐
            //getInputStream() : post 형태로 오는 것을 받을 수 있음
            RequestLogin creds = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);

            //UsernamePasswordAuthenticationToken 토큰 생성 후 AuthenticationManager에 인증작업 요청
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    //  "/login"시 3번째로 탐
    // 토큰 발행해야함
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        //log.debug(((User)authResult.getPrincipal()).getUsername());
        String userName = ((User)authResult.getPrincipal()).getUsername();
    }


}
