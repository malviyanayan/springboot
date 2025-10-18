package com.practice.onetoone.entity.onetomany;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "authers")
public class Auther {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auther_id")
    private Integer id;

    @Column(name = "auther_name")
    private String name;


    @OneToMany(mappedBy = "auther", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books ;
}
