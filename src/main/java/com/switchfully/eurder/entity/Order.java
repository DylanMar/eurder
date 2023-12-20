package com.switchfully.eurder.entity;

import java.util.List;
import java.util.UUID;

public class Order {

    private final UUID orderId;
    private final List<ItemGroup> itemGroups;
    private final double totalPrice;
    private final User customer;

    public Order(List<ItemGroup> itemGroups, User user) {
        this.orderId = UUID.randomUUID();
        this.itemGroups = itemGroups;
        this.totalPrice = itemGroups.stream().mapToDouble(ItemGroup::getTotalPrice).sum();
        this.customer = user;
    }

    public UUID getId() {
        return orderId;
    }

    public List<ItemGroup> getItemGroups() {
        return itemGroups;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public User getCustomer() {
        return customer;
    }

}
