package com.example.aistudyassistant.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class ChatHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private Long studentId;
    @Lob private String userMessage;
    @Lob private String aiResponse;
    private String agentType;
    private Instant createdAt = Instant.now();
    protected ChatHistory() {}
    public ChatHistory(Long studentId, String userMessage, String aiResponse, String agentType){this.studentId=studentId;this.userMessage=userMessage;this.aiResponse=aiResponse;this.agentType=agentType;}
}
