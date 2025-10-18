package com.projects.myauthapp.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {

    private Integer id;
    private String name;
    private String email;
    private String password;

    private List<Journal> journalList;
}
