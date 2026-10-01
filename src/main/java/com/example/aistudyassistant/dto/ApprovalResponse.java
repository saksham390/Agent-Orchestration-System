package com.example.aistudyassistant.dto;

import com.example.aistudyassistant.entity.ApprovalRequest;

import java.time.Instant;

public record ApprovalResponse(Long id, String actionType, String description, String payload,
                               ApprovalRequest.Status status, Instant createdAt, Instant decidedAt) {
    public static ApprovalResponse from(ApprovalRequest approval) {
        return new ApprovalResponse(approval.getId(), approval.getActionType(), approval.getDescription(),
                approval.getPayload(), approval.getStatus(), approval.getCreatedAt(), approval.getDecidedAt());
    }
}
