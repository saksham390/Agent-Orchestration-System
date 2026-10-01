package com.example.aistudyassistant.entity;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String name;
    @Column(unique = true) private String email;
    protected Student() {}
    public Student(String name, String email){this.name=name;this.email=email;}
    public Long getId(){return id;} public String getName(){return name;} public String getEmail(){return email;}
}
