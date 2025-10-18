package com.nayan.dofocus.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIResponse {

    private String status;
    private int statusCode;
    private String message;    // "User found", "User not found" etc
    private Object data;            // actual data (user object, list, etc)

    public APIResponse(String status,int statusCode, String message, Object data) {
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    // getters and setters
}

