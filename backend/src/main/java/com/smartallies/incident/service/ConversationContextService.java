package com.smartallies.incident.service;

import com.smartallies.incident.model.ConversationContext;
import com.smartallies.incident.model.WorkflowState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class ConversationContextService {

    private final Map<String, ConversationContext> contextStore = new ConcurrentHashMap<>();

    public ConversationContext getOrCreateContext(String sessionId) {
        return contextStore.computeIfAbsent(sessionId, id -> {
            log.info("Creating new conversation context for session: {}", id);
            return ConversationContext.builder()
                    .sessionId(id)
                    .workflowState(WorkflowState.INITIAL)
                    .build();
        });
    }

    public ConversationContext getContext(String sessionId) {
        return contextStore.get(sessionId);
    }

    public void updateContext(ConversationContext context) {
        log.debug("Updating context for session: {}, state: {}", 
                  context.getSessionId(), 
                  context.getWorkflowState());
        contextStore.put(context.getSessionId(), context);
    }

    public void clearContext(String sessionId) {
        log.info("Clearing context for session: {}", sessionId);
        contextStore.remove(sessionId);
    }

    public boolean hasContext(String sessionId) {
        return contextStore.containsKey(sessionId);
    }
}
