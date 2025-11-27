package com.smartallies.incident.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class EmergencyConfig {

    @Value("${emergency.phone.police}")
    private String policeNumber;

    @Value("${emergency.phone.ambulance}")
    private String ambulanceNumber;

    @Value("${emergency.phone.fire}")
    private String fireNumber;

    @Value("${emergency.phone.samaritan}")
    private String samaritanNumber;
}
