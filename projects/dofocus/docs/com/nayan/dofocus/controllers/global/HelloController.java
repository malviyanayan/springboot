package com.nayan.dofocus.controllers.global;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello world I am spring boot application !!";
    }
}
