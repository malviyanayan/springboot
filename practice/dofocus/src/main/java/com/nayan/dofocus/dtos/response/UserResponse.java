package com.nayan.dofocus.dtos.response;

import com.nayan.dofocus.entities.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
@ToString
public class UserResponse {

    private String name ;
    private String email;
    private Role role;
    private Timestamp createdAt;

    public UserResponse(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
    }
}
