package com.hgstudy.userservice.controller;

import com.hgstudy.userservice.dto.UserDto;
import com.hgstudy.userservice.service.UserService;
import com.hgstudy.userservice.vo.Greeting;
import com.hgstudy.userservice.vo.RequestUser;
import com.hgstudy.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

//    private Environment env;

//    @Autowired
    private final Greeting greeting;

    private final UserService userService;

//    @Autowired
//    public UserController(Environment env) {
//        this.env = env;
//    }

    @GetMapping("/health_check")
    public String status(){
        return "It's Working in User Service";
    }

    @GetMapping("/welcome")
    public String welcome(){
//        return env.getProperty("greeting.message");
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser requestUser){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(requestUser,UserDto.class);
        userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(userDto,ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

}
