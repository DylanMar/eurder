package com.switchfully.eurder.dto;


import java.util.UUID;

public class CreateItemGroupDto {

    private final UUID itemId;
    private final int amount;

    public CreateItemGroupDto(String itemId, int amount) {
        this.itemId = UUID.fromString(itemId);
        this.amount = amount;
    }

    public UUID getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

}
