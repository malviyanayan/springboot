package com.nayan.myapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_username", columnList = "username")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NonNull
    @Column(name = "username", unique = true, length = 100)
    private String username;

    
    @NonNull
    private String password;


    private List<Journal> journals;

}

