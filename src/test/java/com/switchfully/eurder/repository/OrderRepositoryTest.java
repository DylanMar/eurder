package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.ItemGroup;
import com.switchfully.eurder.domain.Order;
import com.switchfully.eurder.domain.Role;
import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.exception.NotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryTest {

    private static OrderRepository orderRepository;
    private static Order order;
    private static User user;
    private static ArrayList<ItemGroup> itemGroup = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        orderRepository = new OrderRepository();
        user = new User("firstname", "lastname", "email", "address", "0123456789", Role.CUSTOMER);
        itemGroup.add(new ItemGroup(UUID.randomUUID(), 10, LocalDate.now(), 50.0));
        order = new Order(itemGroup, user);
        orderRepository.addOrder(order);
    }

    @Test
    void getOrderById_ExistingId_ReturnsOrder() {
        UUID orderId = orderRepository.getAllOrders().getFirst().getId();
        Order retrievedOrder = orderRepository.getItemById(orderId.toString());
        assertNotNull(retrievedOrder);
        assertEquals(orderId, retrievedOrder.getId());
    }

    @Test
    void getOrderById_NonExistingId_ThrowsNotFoundException() {
        String nonExistingId = UUID.randomUUID().toString();
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> orderRepository.getItemById(nonExistingId));
        assertEquals("Order not found with id: " + nonExistingId, exception.getMessage());
    }

    @Test
    void addOrder_NewOrder_ReturnsAddedOrder() {
        Order addedOrder = orderRepository.addOrder(order);

        assertNotNull(addedOrder.getId());
        assertEquals(order.getId(), addedOrder.getId());
    }

    @Test
    void getAllOrders_ReturnsAllOrdersInRepository() {
        List<Order> allOrders = orderRepository.getAllOrders();
        assertNotNull(allOrders);
        assertNotNull(allOrders);
    }

}
