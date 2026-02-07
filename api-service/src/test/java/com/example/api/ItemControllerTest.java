package com.example.api;

import com.example.api.model.Item;
import com.example.api.repo.ItemRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemControllerTest {

    @Mock
    private ItemRepo repo;

    @InjectMocks
    private ItemController controller;

    @Test
    void all_returnsListFromRepo() {
        Item item = new Item("test-item");
        when(repo.findAll()).thenReturn(List.of(item));

        List<Item> result = controller.all();

        assertEquals(1, result.size());
        assertEquals("test-item", result.get(0).getName());
    }

    @Test
    void create_validName_savesAndReturns() {
        Item saved = new Item("novi");
        when(repo.save(any(Item.class))).thenReturn(saved);

        Item result = controller.create(Map.of("name", "novi"));

        assertEquals("novi", result.getName());
    }

    @Test
    void create_emptyName_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> controller.create(Map.of("name", "")));
    }

    @Test
    void create_missingName_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> controller.create(Map.of()));
    }
}