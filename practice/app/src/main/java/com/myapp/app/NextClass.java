package com.myapp.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NextClass {

    @GetMapping("next")
    public String sayHello(){
        return "Hello G";
    }
}
