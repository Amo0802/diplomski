package com.example.diplomski.notify;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class NotifyClient {
    private final RestClient client;

    public NotifyClient(@Value("${notify.baseUrl}") String baseUrl) {
        this.client = RestClient.builder().baseUrl(baseUrl).build();
    }

    public Map<?, ?> send(String message) {
        return client.post()
                .uri("/notify")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("message", message))
                .retrieve()
                .body(Map.class);
    }
}
