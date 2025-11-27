package com.smartallies.incident.controller;

import com.smartallies.incident.dto.ChatRequest;
import com.smartallies.incident.dto.ChatResponse;
import com.smartallies.incident.service.ChatOrchestrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatController {

    private final ChatOrchestrationService orchestrationService;

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@Valid @RequestBody ChatRequest request) {
        log.info("Received chat request from session: {}", request.getSessionId());
        
        try {
            ChatResponse response = orchestrationService.processMessage(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error processing chat request", e);
            return ResponseEntity.internalServerError()
                    .body(ChatResponse.builder()
                            .message("I encountered an error processing your request. Please try again.")
                            .build());
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Incident Reporting Backend is running");
    }
}
