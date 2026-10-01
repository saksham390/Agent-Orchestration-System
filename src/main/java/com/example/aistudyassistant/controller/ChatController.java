package com.example.aistudyassistant.controller;

import com.example.aistudyassistant.dto.ChatRequest;
import com.example.aistudyassistant.dto.ChatResponse;
import com.example.aistudyassistant.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "${app.frontend-url:http://localhost:5173}")
public class ChatController {
    private final ChatService chatService;
    public ChatController(ChatService chatService) { this.chatService = chatService; }
    @PostMapping
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {
        String message = request.message() == null ? "" : request.message().trim();
        String conversationId = request.conversationId() == null || request.conversationId().isBlank() ? "default" : request.conversationId();
        return ResponseEntity.ok(chatService.handle(message, conversationId));
    }
}
