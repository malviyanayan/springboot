package com.projects.myauthapp.apicontrollers.general;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/auth")
public class AuthController {

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(){
        return null;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(){
        return null;
    }

}
