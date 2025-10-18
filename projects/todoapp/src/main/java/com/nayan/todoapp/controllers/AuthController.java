package com.nayan.todoapp.controllers;

import com.nayan.todoapp.dtos.LoginRequest;
import com.nayan.todoapp.dtos.RegisterRequest;
import com.nayan.todoapp.entities.User;
import com.nayan.todoapp.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authManager;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest req) {
        if (userRepo.existsByUsername(req.getUsername())) {
            return "Username already taken";
        }
        User u = new User();
        u.setUsername(req.getUsername());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        userRepo.save(u);
        return "Registered";
    }

    // Session-based manual login. Returns 200 and sets JSESSIONID.
    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest req, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());
        Authentication auth = authManager.authenticate(token);
        // on success Spring Security will store Authentication in SecurityContext via filter
        // ensure session exists so JSESSIONID is issued
        request.getSession(true);
        return "Logged in";
    }

    // Logout will be handled by /auth/logout (POST) because of SecurityConfig
    @PostMapping("/logout")
    public String logoutNote() {
        return "Logged out";
    }
}
