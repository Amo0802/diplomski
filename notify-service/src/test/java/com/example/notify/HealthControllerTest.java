package com.example.notify;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HealthControllerTest {

    private final HealthController controller = new HealthController();

    @Test
    void health_returnsOk() throws InterruptedException {
        ResponseEntity<Map<String, String>> response = controller.health();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("healthy", response.getBody().get("status"));
    }

    @Test
    void healthBuggy_returns500() {
        ResponseEntity<Map<String, String>> response = controller.healthBuggy();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("error", response.getBody().get("status"));
    }
}