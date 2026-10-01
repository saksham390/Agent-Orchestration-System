package com.example.aistudyassistant.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class ApprovalRequest {
    public enum Status { PENDING, APPROVED, REJECTED }
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private Long studentId;
    private String actionType;
    @Column(length = 1000) private String description;
    @Lob private String payload;
    @Enumerated(EnumType.STRING) private Status status = Status.PENDING;
    private Instant createdAt = Instant.now();
    private Instant decidedAt;
    protected ApprovalRequest() {}
    public ApprovalRequest(Long studentId, String actionType, String description, String payload) { this.studentId=studentId; this.actionType=actionType; this.description=description; this.payload=payload; }
    public Long getId(){return id;} public Long getStudentId(){return studentId;} public String getActionType(){return actionType;} public String getDescription(){return description;} public String getPayload(){return payload;} public Status getStatus(){return status;} public Instant getCreatedAt(){return createdAt;} public Instant getDecidedAt(){return decidedAt;}
    public void decide(Status status){this.status=status; this.decidedAt=Instant.now();}
}
