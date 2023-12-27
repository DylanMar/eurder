package com.switchfully.eurder.dto;


public class CreateItemGroupDto {

    private final Long itemId;
    private final int amount;

    public CreateItemGroupDto(Long itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public Long getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

}
