package com.projects.myauthapp.services;

import com.projects.myauthapp.entities.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {

    public HashMap<String, String> verifyUser(User user) {
        HashMap<String, String> verify = new HashMap<>();

        // Validate Name
        String name = user.getName();
        if (name == null || name.trim().isEmpty()) {
            verify.put("name", "Name cannot be empty");
        } else if (name.length() < 3) {
            verify.put("name", "Name must be at least 3 characters");
        }

        // Validate Email
        String email = user.getEmail();
        if (email == null || email.trim().isEmpty()) {
            verify.put("email", "Email cannot be empty");
        } else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            verify.put("email", "Invalid email format");
        }

        // Validate Password
        String password = user.getPassword();
        if (password == null || password.trim().isEmpty()) {
            verify.put("password", "Password cannot be empty");
        } else if (password.length() < 6) {
            verify.put("password", "Password must be at least 6 characters");
        }

        return verify;
    }
}
