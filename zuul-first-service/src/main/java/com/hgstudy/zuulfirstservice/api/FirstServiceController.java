package com.hgstudy.zuulfirstservice.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// http://localhost:8081/welcome
// http://localhost:8081/firest-service/welcome
@RestController
@RequestMapping("/first-service")
@Slf4j
public class FirstServiceController {

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the First service.";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header ){
        log.info(header);
        return "Hello world in first service.";
    }
}

