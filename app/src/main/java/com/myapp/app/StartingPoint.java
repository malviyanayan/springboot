package com.myapp.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartingPoint {

    @GetMapping("/")
    public String startPoint(){
        return "Hello My Child";
    }
}
