package com.example.aistudyassistant.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class StudyPlan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private Long studentId;
    private String title;
    @Lob private String description;
    private Instant createdAt = Instant.now();
    protected StudyPlan() {}
    public StudyPlan(Long studentId, String title, String description){this.studentId=studentId;this.title=title;this.description=description;}
    public Long getId(){return id;} public Long getStudentId(){return studentId;} public String getTitle(){return title;} public String getDescription(){return description;} public Instant getCreatedAt(){return createdAt;}
}
