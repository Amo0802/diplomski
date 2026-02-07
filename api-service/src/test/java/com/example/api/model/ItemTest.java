package com.example.api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void constructor_setsName() {
        Item item = new Item("test");
        assertEquals("test", item.getName());
    }

    @Test
    void setName_updatesName() {
        Item item = new Item("old");
        item.setName("new");
        assertEquals("new", item.getName());
    }

    @Test
    void createdAt_isNotNull() {
        Item item = new Item("test");
        assertNotNull(item.getCreatedAt());
    }
}