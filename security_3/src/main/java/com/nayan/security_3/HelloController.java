package com.nayan.security_3;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public String hello(){
        return "hello <hr /> <a href='hello/pro'>Pro Link</a> <hr />  <a href='hello/info'>Info Link</a>";
    }

    @GetMapping("/info")
    public String info(){
        return "Information Here <hr />  <a href='/hello'>home Link</a>";
    }

    @GetMapping("/pro")
    public String pro(){
        return "Problem here <hr />  <a href='/hello'>home Link</a>";
    }
}
