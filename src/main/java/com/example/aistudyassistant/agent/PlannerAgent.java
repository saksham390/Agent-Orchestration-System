package com.example.aistudyassistant.agent;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class PlannerAgent {
    private final ChatClient chatClient;
    public PlannerAgent(ChatClient.Builder builder) { this.chatClient = builder.defaultSystem("Create a practical study plan. Include each day, topic, tasks, estimated time, and practice recommendation. Return readable Markdown.").build(); }
    public String handle(String message) { return chatClient.prompt().user(message).call().content(); }
}
