package com.switchfully.eurder.dto;

import java.util.UUID;

public class ItemDto {

    private final UUID id;
    private final String name;
    private final String description;
    private final double price;
    private final int amountInStock;

    public ItemDto(UUID id, String name, String description, double price, int amountInStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amountInStock = amountInStock;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

}
