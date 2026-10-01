package com.example.aistudyassistant.controller;

import com.example.aistudyassistant.dto.ApprovalResponse;
import com.example.aistudyassistant.service.ApprovalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/approvals")
@CrossOrigin(origins = "${app.frontend-url:http://localhost:5173}")
public class ApprovalController {
    private final ApprovalService approvalService;
    public ApprovalController(ApprovalService approvalService) { this.approvalService = approvalService; }
    @GetMapping("/pending") public List<ApprovalResponse> pending() { return approvalService.pending(); }
    @PostMapping("/{id}/approve") public ResponseEntity<ApprovalResponse> approve(@PathVariable Long id) { return ResponseEntity.ok(approvalService.approve(id)); }
    @PostMapping("/{id}/reject") public ResponseEntity<ApprovalResponse> reject(@PathVariable Long id) { return ResponseEntity.ok(approvalService.reject(id)); }
}
