package com.example.notify;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() throws InterruptedException {

        return ResponseEntity.ok(Map.of(
                "status", "healthy",
                "message", "Notify service is running"
        ));
    }

    @GetMapping("/health-buggy")
    public ResponseEntity<Map<String, String>> healthBuggy() {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "status", "error",
                        "message", "This endpoint always fails (for testing)"
                ));
    }
}