package com.example.aistudyassistant.service;

import com.example.aistudyassistant.entity.ApprovalRequest;
import com.example.aistudyassistant.repository.ApprovalRequestRepository;
import com.example.aistudyassistant.repository.QuizRepository;
import com.example.aistudyassistant.repository.StudyPlanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApprovalServiceTest {
    @Mock ApprovalRequestRepository approvals;
    @Mock StudyPlanRepository studyPlans;
    @Mock QuizRepository quizzes;
    @InjectMocks ApprovalService service;

    @Test
    void rejectsSecondDecision() {
        ApprovalRequest request = new ApprovalRequest(1L, "SAVE_STUDY_PLAN", "Save plan", "plan content");
        request.decide(ApprovalRequest.Status.APPROVED);
        when(approvals.findById(7L)).thenReturn(Optional.of(request));

        assertThatThrownBy(() -> service.reject(7L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Approval request has already been processed.");
        verifyNoInteractions(studyPlans, quizzes);
    }

    @Test
    void rejectsUnknownActionTypeBeforeSaving() {
        assertThatThrownBy(() -> service.create("DELETE_EVERYTHING", "bad", "payload"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid action type.");
        verifyNoInteractions(approvals);
    }
}
