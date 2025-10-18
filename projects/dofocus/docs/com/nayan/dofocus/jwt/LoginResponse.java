package com.nayan.dofocus.jwt;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoginResponse {
    private String jwtToken;

    private String email;
    private List<String> roles;

    public LoginResponse(String username, List<String> roles, String jwtToken) {
        this.email = username;
        this.roles = roles;
        this.jwtToken = jwtToken;
    }

}

