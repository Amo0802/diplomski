package com.example.notify;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class NotifyController {

    @Value("${notify.bugMode:false}")
    private boolean bugMode;

    @Value("${notify.delayMs:0}")
    private long delayMs;

    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of("status", "UP", "bugMode", bugMode, "delayMs", delayMs);
    }

    @PostMapping("/notify")
    public Map<String, Object> notify(@RequestBody Map<String, Object> body) throws InterruptedException {
        if (delayMs > 0) Thread.sleep(delayMs);

        if (bugMode) {
            throw new NotifyException("Simulated failure (BUG_MODE=true)");
        }

        return Map.of("ok", true, "received", body);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private static class NotifyException extends RuntimeException {
        public NotifyException(String msg) {
            super(msg);
        }
    }

}