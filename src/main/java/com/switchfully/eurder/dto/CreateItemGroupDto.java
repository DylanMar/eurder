package com.switchfully.eurder.dto;


public class CreateItemGroupDto {

    private final Long itemId;
    private final int amount;
    private final Long orderId;

    public CreateItemGroupDto(Long itemId, int amount, Long orderId) {
        this.itemId = itemId;
        this.amount = amount;
        this.orderId = orderId;
    }

    public Long getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    public Long getOrderId() {
        return orderId;
    }

}
