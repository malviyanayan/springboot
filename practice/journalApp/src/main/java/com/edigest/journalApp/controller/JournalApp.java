package com.edigest.journalApp.controller;

import com.edigest.journalApp.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/myapp")
public class JournalApp {

    @GetMapping("/user")
    public User getUser(){
        return new User("Anshul Namdeo", 21);
    }

    @GetMapping("/getusers")
    public ArrayList<User> getUsers(){
        ArrayList<User> list = new ArrayList<>();
        list.add(new User("Nayan Soni", 21));
        list.add(new User("Anuj Vishwakarma", 22));
        list.add(new User("Anshul Namdeo", 23));
        return list;
    }

    @GetMapping("/next")
    public String useGet(){
        return "Get Method Used";
    }

    @PostMapping("/next")
    public String usePodt(){
        return "Post Method Used";
    }
}
