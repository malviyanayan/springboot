package com.nayan.security_1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, secured world!";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Welcome to your dashboard!";
    }

    @GetMapping("/profile")
    public String profile() {
        return "This is your profile page.";
    }

    @GetMapping("/public")
    public String publicPage() {
        return "This page is public, no login required!";
    }
}
