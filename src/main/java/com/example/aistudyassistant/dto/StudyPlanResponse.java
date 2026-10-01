package com.example.aistudyassistant.dto;

import java.util.List;

public record StudyPlanResponse(String title, String description, List<StudyDay> days) {
    public record StudyDay(int day, String topic, List<String> tasks, String estimatedTime, String practiceRecommendation) {
    }
}
