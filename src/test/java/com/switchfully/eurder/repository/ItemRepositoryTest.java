package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    private ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        itemRepository = new ItemRepository();
    }

    @Test
    void getItemById_ExistingId_ReturnsItem() {
        UUID itemId = itemRepository.getAllItems().get(0).getId();
        Item retrievedItem = itemRepository.getItemById(itemId);
        assertNotNull(retrievedItem);
        assertEquals(itemId, retrievedItem.getId());
    }

    @Test
    void getItemById_NonExistingId_ThrowsNotFoundException() {
        UUID nonExistingId = UUID.randomUUID();
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> itemRepository.getItemById(nonExistingId));
        assertEquals("Item not found with Id: " + nonExistingId, exception.getMessage());
    }

    @Test
    void addItem_NewItem_ReturnsAddedItem() {
        Item newItem = new Item("New Item", "Description", 15.0, 5);
        Item addedItem = itemRepository.addItem(newItem);

        assertNotNull(addedItem.getId());
        assertEquals(newItem.getName(), addedItem.getName());
        assertEquals(newItem.getDescription(), addedItem.getDescription());
        assertEquals(newItem.getPrice(), addedItem.getPrice());
        assertEquals(newItem.getAmountInStock(), addedItem.getAmountInStock());
    }

    @Test
    void getAllItems_ReturnsAllItemsInRepository() {
        List<Item> allItems = itemRepository.getAllItems();
        assertNotNull(allItems);
        assertNotNull(allItems);
    }
}
