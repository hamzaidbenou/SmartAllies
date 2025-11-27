package com.smartallies.incident;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.ai.ollama.base-url=http://localhost:11434"
})
class IncidentReportingApplicationTests {

    @Test
    void contextLoads() {
        // Test that Spring context loads successfully
    }
}
