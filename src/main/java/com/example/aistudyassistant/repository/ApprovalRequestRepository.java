package com.example.aistudyassistant.repository;

import com.example.aistudyassistant.entity.ApprovalRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApprovalRequestRepository extends JpaRepository<ApprovalRequest, Long> {
    List<ApprovalRequest> findByStatusOrderByCreatedAtDesc(ApprovalRequest.Status status);
}
