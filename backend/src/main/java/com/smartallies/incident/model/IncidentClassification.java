package com.smartallies.incident.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidentClassification {
    
    private IncidentType type;
    private Double confidence;
    private String reasoning;
}
