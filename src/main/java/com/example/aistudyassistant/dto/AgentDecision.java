package com.example.aistudyassistant.dto;

public record AgentDecision(AgentType agent, String reason) {
    public enum AgentType { STUDY, QUIZ, PLANNER }
}
