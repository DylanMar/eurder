package com.switchfully.eurder.dto.itemdto;

public class ItemResupplyUrgencyDto {
    private final Long itemId;
    private final String name;
    private final String description;
    private final double price;
    private final ResupplyUrgency resupplyUrgency;

    public ItemResupplyUrgencyDto(Long itemId, String name, String description, double price, ResupplyUrgency resupplyUrgency) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.resupplyUrgency = resupplyUrgency;
    }

    public Long getItemId() {
        return itemId;
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

    public ResupplyUrgency getResupplyUrgency() {
        return resupplyUrgency;
    }

}
