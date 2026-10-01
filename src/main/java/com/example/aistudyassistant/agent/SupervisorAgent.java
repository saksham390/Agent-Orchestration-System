package com.example.aistudyassistant.agent;

import com.example.aistudyassistant.dto.AgentDecision;
import org.springframework.stereotype.Service;

@Service
public class SupervisorAgent {
    public AgentDecision decide(String message) {
        String text = message.toLowerCase();
        if (text.contains("quiz") || text.contains("question") || text.contains("test me")) {
            return new AgentDecision(AgentDecision.AgentType.QUIZ, "The student requested questions or a quiz.");
        }
        if (text.contains("plan") || text.contains("schedule") || text.contains("study for")) {
            return new AgentDecision(AgentDecision.AgentType.PLANNER, "The student requested a study plan.");
        }
        return new AgentDecision(AgentDecision.AgentType.STUDY, "The student requested an explanation or learning help.");
    }
}
