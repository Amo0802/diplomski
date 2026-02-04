package com.example.diplomski;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class NotifyController {
    private final NotifyClient notify;

    public NotifyController(NotifyClient notify) {
        this.notify = notify;
    }

    @PostMapping("/trigger-notify")
    public Map<?, ?> trigger(@RequestBody Map<String, String> body) {
        String msg = body.getOrDefault("message", "hello");
        return notify.send(msg);
    }
}
