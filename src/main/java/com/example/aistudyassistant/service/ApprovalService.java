package com.example.aistudyassistant.service;

import com.example.aistudyassistant.dto.ApprovalResponse;
import com.example.aistudyassistant.entity.ApprovalRequest;
import com.example.aistudyassistant.entity.Quiz;
import com.example.aistudyassistant.entity.StudyPlan;
import com.example.aistudyassistant.repository.ApprovalRequestRepository;
import com.example.aistudyassistant.repository.QuizRepository;
import com.example.aistudyassistant.repository.StudyPlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ApprovalService {
    private final ApprovalRequestRepository approvals;
    private final StudyPlanRepository studyPlans;
    private final QuizRepository quizzes;

    public ApprovalService(ApprovalRequestRepository approvals, StudyPlanRepository studyPlans, QuizRepository quizzes) {
        this.approvals = approvals; this.studyPlans = studyPlans; this.quizzes = quizzes;
    }

    public List<ApprovalResponse> pending() {
        return approvals.findByStatusOrderByCreatedAtDesc(ApprovalRequest.Status.PENDING).stream().map(ApprovalResponse::from).toList();
    }

    public ApprovalRequest create(String actionType, String description, String payload) {
        if (!actionType.equals("SAVE_STUDY_PLAN") && !actionType.equals("SAVE_QUIZ")) throw new IllegalArgumentException("Invalid action type.");
        return approvals.save(new ApprovalRequest(1L, actionType, description, payload));
    }

    @Transactional
    public ApprovalResponse approve(Long id) {
        ApprovalRequest request = getPending(id);
        if (request.getActionType().equals("SAVE_STUDY_PLAN")) studyPlans.save(new StudyPlan(1L, "Approved study plan", request.getPayload()));
        else if (request.getActionType().equals("SAVE_QUIZ")) quizzes.save(new Quiz(1L, "Approved quiz", request.getPayload()));
        else throw new IllegalArgumentException("Invalid action type.");
        request.decide(ApprovalRequest.Status.APPROVED);
        return ApprovalResponse.from(approvals.save(request));
    }

    @Transactional
    public ApprovalResponse reject(Long id) {
        ApprovalRequest request = getPending(id);
        request.decide(ApprovalRequest.Status.REJECTED);
        return ApprovalResponse.from(approvals.save(request));
    }

    private ApprovalRequest getPending(Long id) {
        ApprovalRequest request = approvals.findById(id).orElseThrow(() -> new IllegalArgumentException("Approval request not found."));
        if (request.getStatus() != ApprovalRequest.Status.PENDING) throw new IllegalStateException("Approval request has already been processed.");
        return request;
    }
}
