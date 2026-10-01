package com.example.aistudyassistant.repository;

import com.example.aistudyassistant.entity.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {}
