package com.example.api;

import com.example.api.model.Item;
import com.example.api.repo.ItemRepo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/items")
public class ItemController {

    //Test 1 cicd eks 5

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
