package com.example.aistudyassistant.service;

import com.example.aistudyassistant.agent.*;
import com.example.aistudyassistant.dto.AgentDecision;
import com.example.aistudyassistant.dto.ChatResponse;
import com.example.aistudyassistant.entity.ApprovalRequest;
import com.example.aistudyassistant.entity.ChatHistory;
import com.example.aistudyassistant.repository.ChatHistoryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final SupervisorAgent supervisor;
    private final StudyAgent study;
    private final QuizAgent quiz;
    private final PlannerAgent planner;
    private final ApprovalService approvalService;
    private final ChatHistoryRepository history;
    private final boolean demoMode;

    public ChatService(SupervisorAgent supervisor, StudyAgent study, QuizAgent quiz, PlannerAgent planner,
                       ApprovalService approvalService, ChatHistoryRepository history,
                       @Value("${app.demo-mode:true}") boolean demoMode) {
        this.supervisor=supervisor; this.study=study; this.quiz=quiz; this.planner=planner; this.approvalService=approvalService; this.history=history; this.demoMode=demoMode;
    }

    public ChatResponse handle(String message, String conversationId) {
        if (message.isBlank()) throw new IllegalArgumentException("Message cannot be empty.");
        AgentDecision decision = supervisor.decide(message);
        String response = switch (decision.agent()) {
            case QUIZ -> askQuiz(message);
            case PLANNER -> askPlanner(message);
            case STUDY -> askStudy(message, conversationId);
            default -> throw new IllegalArgumentException("Invalid supervisor response.");
        };
        String lower = message.toLowerCase();
        if (lower.contains("save") && decision.agent() == AgentDecision.AgentType.PLANNER) {
            ApprovalRequest approval = approvalService.create("SAVE_STUDY_PLAN", "Save the generated study plan.", response);
            return ChatResponse.approval("I created the plan. Approval is required before saving it.", approval.getId());
        }
        if (lower.contains("save") && decision.agent() == AgentDecision.AgentType.QUIZ) {
            ApprovalRequest approval = approvalService.create("SAVE_QUIZ", "Save the generated quiz.", response);
            return ChatResponse.approval("I created the quiz. Approval is required before saving it.", approval.getId());
        }
        history.save(new ChatHistory(1L, message, response, decision.agent().name()));
        return ChatResponse.answer(decision.agent().name(), response);
    }

    private String askStudy(String message, String conversationId) { return callOrDemo(() -> study.handle(message, conversationId), "Binary search checks the middle item of a sorted collection and eliminates half the remaining items each step.\n\nExample: searching 7 in [1, 3, 5, 7, 9].\n\nImportant points:\n- The data must be sorted.\n- Time complexity is O(log n).\n\nSummary: binary search is fast because it halves the search space."); }
    private String askQuiz(String message) { return callOrDemo(() -> quiz.handle(message), "1. Which structure follows FIFO?\nA. Stack\nB. Queue\nC. Tree\nD. Graph\n\nCorrect answer: B. Queue\nExplanation: the first item added is the first item removed."); }
    private String askPlanner(String message) { return callOrDemo(() -> planner.handle(message), "Day 1: Arrays - learn traversal and solve two easy problems.\nDay 2: Strings - practice counting and searching.\nDay 3: Binary Search - learn the invariant and solve two problems.\nEstimated time: 90 minutes per day."); }
    private String callOrDemo(java.util.function.Supplier<String> call, String fallback) {
        if (demoMode) return fallback;
        try { return call.get(); } catch (RuntimeException exception) { return "The AI service is unavailable right now. Please check GEMINI_API_KEY and try again."; }
    }
}
