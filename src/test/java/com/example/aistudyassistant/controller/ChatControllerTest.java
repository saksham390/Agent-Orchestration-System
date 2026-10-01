package com.example.aistudyassistant.controller;

import com.example.aistudyassistant.dto.ChatResponse;
import com.example.aistudyassistant.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ChatControllerTest {
    @Test
    void suppliesDefaultConversationIdWhenRequestOmitsIt() {
        ChatService service = mock(ChatService.class);
        ChatController controller = new ChatController(service);
        when(service.handle("Explain arrays", "default")).thenReturn(ChatResponse.answer("STUDY", "Arrays explained"));

        ResponseEntity<ChatResponse> response = controller.chat(new com.example.aistudyassistant.dto.ChatRequest("Explain arrays", null));

        assertThat(response.getBody().response()).isEqualTo("Arrays explained");
        verify(service).handle("Explain arrays", "default");
    }
}
