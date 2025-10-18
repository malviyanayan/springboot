package com.nayan.dofocus.controllers.users;


import com.nayan.dofocus.dtos.APIResponse;
import com.nayan.dofocus.dtos.response.UserResponse;
import com.nayan.dofocus.entities.User;
import com.nayan.dofocus.jwt.JwtUtils;
import com.nayan.dofocus.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtUtils jwtUtils;


    // ----------------- Sign up -----------------
    @PostMapping("/signup")
    public ResponseEntity<APIResponse> signup(@RequestBody User user) {
        String validation = userService.validateUser(user);

        APIResponse response = switch (validation) {
            case "NAME_INVALID" -> new APIResponse("BAD_REQUEST", HttpStatus.BAD_REQUEST.value(),
                    "Name must be at least 3 characters long and contain only letters", null);
            case "EMAIL_INVALID" -> new APIResponse("BAD_REQUEST", HttpStatus.BAD_REQUEST.value(),
                    "Email is invalid", null);
            case "PASSWORD_INVALID" -> new APIResponse("BAD_REQUEST", HttpStatus.BAD_REQUEST.value(),
                    "Password must be at least 8 characters, include 1 uppercase letter, 1 number, and 1 special character", null);
            case "EMAIL_EXISTS" -> new APIResponse("CONFLICT", HttpStatus.CONFLICT.value(),
                    "Email already registered", null);
            case "VALID" -> {
                User savedUser = userService.saveUser(user);
                yield new APIResponse("CREATED", HttpStatus.CREATED.value(),
                        "User registered successfully", null);
            }
            default -> new APIResponse("ERROR", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Something went wrong, try again later", null);
        };

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // ----------------- Sign in (JWT generate karega) -----------------
    @PostMapping("/signin")
    public ResponseEntity<APIResponse> signIn(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // JWT generate karna
            String jwt = jwtUtils.generateTokenFromUsername(userDetails);

            APIResponse response = new APIResponse(
                    "OK",
                    HttpStatus.OK.value(),
                    "Login successful",
                    jwt // Token as data
            );
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception ex) {
            APIResponse response = new APIResponse(
                    "UNAUTHORIZED",
                    HttpStatus.UNAUTHORIZED.value(),
                    "Invalid email or password",
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }


    @GetMapping("/myinfo")
    public ResponseEntity<APIResponse> getUserInfo() {
        // Get authentication object from SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = null;
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName(); // yaha se JWT me se nikala username/email milega
            user = userService.findByEmail(email);   // service call to fetch User entity from DB
        }

        APIResponse response;
        if (user == null) {
            response = new APIResponse(
                    "NOT_FOUND", HttpStatus.NOT_FOUND.value(),
                    "Signin or Register to get access",
                    null
            );
        } else {
            user.setPassword(null); // password hide karna
            response = new APIResponse(
                    "OK", HttpStatus.OK.value(),
                    "User Found",
                    new UserResponse(user)
            );
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
