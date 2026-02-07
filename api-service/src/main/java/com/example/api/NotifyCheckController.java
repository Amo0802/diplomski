package com.example.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class NotifyCheckController {

    @Value("${notify.baseUrl}")
    private String notifyBaseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/notifycheck")
    public ResponseEntity<Map<String, Object>> checkNotify() {
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(
                    notifyBaseUrl + "/health",
                    Map.class
            );

            return ResponseEntity.ok(Map.of(
                    "notifyStatus", "reachable",
                    "notifyResponse", response.getBody(),
                    "httpStatus", response.getStatusCode().value()
            ));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(Map.of(
                            "notifyStatus", "unreachable",
                            "error", e.getMessage()
                    ));
        }
    }
}