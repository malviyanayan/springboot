package com.nayan.dofocusx.controllers;

import com.nayan.dofocusx.models.User;
import com.nayan.dofocusx.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private EmailService emailService;

    private Map<String, String> users = new HashMap<>();

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        if(users.containsKey(user.getUsername())) {
            return "Username already exists!";
        }
        users.put(user.getUsername(), user.getPassword());

        // Optional: send signup email
        emailService.sendEmail(user.getUsername() + "@example.com", "http://dofocusx.com/activate?token=12345");

        return "Registered successfully!";
    }

    @PostMapping("/signin")
    public String signInUser(@RequestBody User user) {
        if(users.containsKey(user.getUsername()) && users.get(user.getUsername()).equals(user.getPassword())) {
            return "Signed in successfully!";
        }
        return "Invalid username or password!";
    }

    @GetMapping("/info")
    public Map<String,String> getInfo() {
        Map<String,String> info = new HashMap<>();
        info.put("appName","DoFocusX");
        info.put("version","1.0");
        info.put("author","Nayan Malviya");
        info.put("description","Test Spring Boot backend for React Vite app");
        return info;
    }
}
