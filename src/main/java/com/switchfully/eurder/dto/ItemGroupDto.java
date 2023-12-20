package com.switchfully.eurder.dto;

import java.time.LocalDate;

public class ItemGroupDto {

    private final Long itemGroupId;
    private final double amount;
    private final LocalDate shippingDate;
    private final double totalPrice;

    public ItemGroupDto(Long itemGroupId, double amount, LocalDate shippingDate, double totalPrice) {
        this.itemGroupId = itemGroupId;
        this.amount = amount;
        this.shippingDate = shippingDate;
        this.totalPrice = totalPrice;
    }

    public Long getItemGroupId() {
        return itemGroupId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

}
