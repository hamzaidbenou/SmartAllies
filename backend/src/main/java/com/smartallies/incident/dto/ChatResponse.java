package com.smartallies.incident.dto;

import com.smartallies.incident.model.IncidentType;
import com.smartallies.incident.model.WorkflowState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {
    
    private String message;
    private IncidentType incidentType;
    private WorkflowState workflowState;
    private List<String> suggestedActions;
    private Map<String, Object> metadata;
    private List<String> resources;
}
