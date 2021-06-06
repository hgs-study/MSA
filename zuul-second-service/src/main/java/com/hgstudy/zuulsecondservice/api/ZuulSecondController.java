package com.hgstudy.zuulsecondservice.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service")
public class ZuulSecondController {

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the Second service";
    }
}

