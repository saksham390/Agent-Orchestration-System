package com.example.aistudyassistant.dto;

public record ChatResponse(String type, String response, Long approvalId) {
    public static ChatResponse answer(String type, String response) {
        return new ChatResponse(type, response, null);
    }

    public static ChatResponse approval(String response, Long approvalId) {
        return new ChatResponse("APPROVAL_REQUIRED", response, approvalId);
    }
}
