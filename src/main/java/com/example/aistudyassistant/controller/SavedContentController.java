package com.example.aistudyassistant.controller;

import com.example.aistudyassistant.entity.Quiz;
import com.example.aistudyassistant.entity.StudyPlan;
import com.example.aistudyassistant.repository.QuizRepository;
import com.example.aistudyassistant.repository.StudyPlanRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/saved")
@CrossOrigin(origins = "${app.frontend-url:http://localhost:5173}")
public class SavedContentController {
    private final StudyPlanRepository studyPlans;
    private final QuizRepository quizzes;

    public SavedContentController(StudyPlanRepository studyPlans, QuizRepository quizzes) {
        this.studyPlans = studyPlans;
        this.quizzes = quizzes;
    }

    @GetMapping("/plans")
    public List<StudyPlan> plans() {
        return studyPlans.findAll();
    }

    @GetMapping("/quizzes")
    public List<Quiz> quizzes() {
        return quizzes.findAll();
    }
}
