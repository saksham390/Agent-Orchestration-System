package com.example.aistudyassistant.agent;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

@Service
public class StudyAgent {
    private final ChatClient chatClient;
    public StudyAgent(ChatClient.Builder builder, ChatMemory chatMemory) {
        this.chatClient = builder.defaultSystem("You are a friendly study tutor. Explain concepts simply with an example, important points, and a short summary.")
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }
    public String handle(String message, String conversationId) {
        return chatClient.prompt().user(message)
                .advisors(advisor -> advisor.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call().content();
    }
}
