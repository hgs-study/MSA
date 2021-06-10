package com.hgstudy.zuulsecondservice.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service")
@Slf4j
public class ZuulSecondController {

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the Second service";
    }


    @GetMapping("/message")
    public String messgae (@RequestHeader("second-request") String header){
        log.info(header);
        return "Hello World in Second Serivce";
    }


    @GetMapping("/check")
    public String check(){
        return "Hi, there. This is a message from Second Service.";
    }

}

