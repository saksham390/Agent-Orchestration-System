package com.example.aistudyassistant.agent;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class QuizAgent {
    private final ChatClient chatClient;
    public QuizAgent(ChatClient.Builder builder) { this.chatClient = builder.defaultSystem("Create a beginner-friendly multiple-choice quiz. Give four options, the correct answer, and a short explanation for each question. Return readable Markdown.").build(); }
    public String handle(String message) { return chatClient.prompt().user(message).call().content(); }
}
