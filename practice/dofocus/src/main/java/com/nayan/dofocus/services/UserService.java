package com.nayan.dofocus.services;

import com.nayan.dofocus.entities.Role;
import com.nayan.dofocus.entities.Status;
import com.nayan.dofocus.entities.Todo;
import com.nayan.dofocus.entities.User;
import com.nayan.dofocus.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpSession session;

    // Regex
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z]{3,}(?: [A-Za-z]+)*$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,}$"
    );

    // Validate signup input
    public String validateUser(User user) {
        if(user.getName() == null || !NAME_PATTERN.matcher(user.getName()).matches()) return "NAME_INVALID";
        if(user.getEmail() == null || !EMAIL_PATTERN.matcher(user.getEmail()).matches()) return "EMAIL_INVALID";
        if(user.getPassword() == null || !PASSWORD_PATTERN.matcher(user.getPassword()).matches()) return "PASSWORD_INVALID";
        if(userRepository.existsByEmail(user.getEmail())) return "EMAIL_EXISTS";
        return "VALID";
    }

    // Signin
    public String signIn(String email, String password) {

        User user = userRepository.findByEmail(email).orElse(null);
        if(user == null) return "EMAIL_NOT_FOUND";

        // Password check
        if(!passwordEncoder.matches(password, user.getPassword())) return "INVALID_PASSWORD";

        // Status check
        switch(user.getStatus()) {
            case BLOCKED: return "USER_BLOCKED";
            case NOT_VERIFIED: return "USER_NOT_VERIFIED";
            case VERIFIED:
                // Store entire user object in session
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(60*10);
                System.out.println(session.getId());
                System.out.println(user);
                return "SUCCESS";
            default: return "UNKNOWN_STATUS";
        }
    }

    // Save user with hashed password, default role & status
    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setStatus(Status.NOT_VERIFIED);
        return userRepository.save(user);
    }

    public User findByEmail(String email){
        Optional<User> temp = userRepository.findByEmail(email);
        return temp.orElse(null);
    }

    public User getCurrentUser(){
        User user = (User)session.getAttribute("user");
        System.out.println(session.getId());
        if(user != null)user.setPassword(null);
        return user;
    }
}
