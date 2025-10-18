package com.myapp.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DogBark {

    @Autowired
    Dog dog;


    @GetMapping("bark-dog")
    public String barkDog(){
        return dog.bark();
    }
}
