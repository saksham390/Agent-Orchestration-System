package com.example.aistudyassistant.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class Quiz {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private Long studentId;
    private String title;
    @Lob private String questions;
    private Instant createdAt = Instant.now();
    protected Quiz() {}
    public Quiz(Long studentId, String title, String questions){this.studentId=studentId;this.title=title;this.questions=questions;}
    public Long getId(){return id;} public Long getStudentId(){return studentId;} public String getTitle(){return title;} public String getQuestions(){return questions;} public Instant getCreatedAt(){return createdAt;}
}
