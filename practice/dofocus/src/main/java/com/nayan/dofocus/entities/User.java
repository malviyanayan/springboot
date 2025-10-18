package com.nayan.dofocus.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_user_email", columnList = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;


    @Column(nullable = false, name = "name", length = 255)
    private String name ;

    @Column(nullable = false, name = "email", unique = true, columnDefinition = "char(255)")
    private String email;

    @Column(nullable = false, name = "password", columnDefinition = "varchar(500)")
    private String password ;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    @Column(name = "verification_code", nullable = true, length = 50)
    private String verificationCode;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Todo> todos;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Note> notes;
}
