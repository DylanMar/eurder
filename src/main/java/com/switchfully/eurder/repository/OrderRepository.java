package com.switchfully.eurder.repository;

import com.switchfully.eurder.entity.Order;
import com.switchfully.eurder.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {

    private final Map<UUID, Order> orders = new HashMap<>();

    public Order getItemById(String id) {
        if (!orders.containsKey(UUID.fromString(id))) {
            throw new NotFoundException("Order not found with id: " + id);
        }
        return orders.get(UUID.fromString(id));
    }

    public Order addOrder(Order order) {
        orders.put(order.getId(), order);
        return order;
    }

    public List<Order> getAllOrders() {
        return orders.values().stream().toList();
    }

}
