package com.example.aistudyassistant.agent;

import com.example.aistudyassistant.dto.AgentDecision;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SupervisorAgentTest {
    private final SupervisorAgent supervisor = new SupervisorAgent();

    @Test
    void routesQuizRequestsToQuizAgent() {
        assertThat(supervisor.decide("Give me five Java questions").agent()).isEqualTo(AgentDecision.AgentType.QUIZ);
    }

    @Test
    void routesPlanRequestsToPlannerAgent() {
        assertThat(supervisor.decide("Create a seven day study plan").agent()).isEqualTo(AgentDecision.AgentType.PLANNER);
    }

    @Test
    void routesExplanationsToStudyAgent() {
        assertThat(supervisor.decide("Explain inheritance").agent()).isEqualTo(AgentDecision.AgentType.STUDY);
    }
}
