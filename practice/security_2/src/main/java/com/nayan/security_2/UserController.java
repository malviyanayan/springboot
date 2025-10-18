package com.nayan.security_2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    // Ye endpoint sirf logged-in user ke liye accessible hoga
    @GetMapping("/profile")
    public String profile(Principal principal) {
        return "Welcome, " + principal.getName() + "! This is your profile.";
    }

    // Spring Security already /login handle karta hai (agar formLogin enable hai)
    // Yaha bas ek dummy endpoint banate hain check karne ke liye
    @GetMapping("/hello")
    public String hello() {
        return "Hello! You are authenticated.";
    }

    // Logout Spring Security handle karega (POST /logout by default)
    // Lekin hum ek custom message de dete hain
    @PostMapping("/logout-success")
    public String logoutSuccess() {
        return "You are logged out successfully!";
    }
}
