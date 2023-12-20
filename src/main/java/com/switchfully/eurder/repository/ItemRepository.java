package com.switchfully.eurder.repository;

import com.switchfully.eurder.entity.Item;
import com.switchfully.eurder.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class ItemRepository {

    private static final Map<UUID, Item> items = new HashMap<>();

    public ItemRepository() {
        Item item;
        for (int i = 1; i < 10; i++) {
            item = new Item("item", "item", 10.5, 10);
            items.put(item.getId(), item);
            System.out.println("Item id: " + item.getId());
        }
    }

    public Item getItemById(UUID id) {
        return items.values()
                .stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Item not found with Id: " + id));
    }

    public Item addItem(Item item) {
        items.put(item.getId(), item);
        return item;
    }

    public List<Item> getAllItems() {
        return items.values().stream().toList();
    }

}
