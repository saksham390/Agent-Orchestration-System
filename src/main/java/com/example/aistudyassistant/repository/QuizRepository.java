package com.example.aistudyassistant.repository;

import com.example.aistudyassistant.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {}
