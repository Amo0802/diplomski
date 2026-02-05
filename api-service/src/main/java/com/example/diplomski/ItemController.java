package com.example.diplomski;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemRepo repo;

    public ItemController(ItemRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Item> all() {
        return repo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item create(@RequestBody Map<String, String> body) {
        String name = body.getOrDefault("name", "").trim();
        if (name.isEmpty()) throw new IllegalArgumentException("name is required");
        return repo.save(new Item(name));
    }
}
