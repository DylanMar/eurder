package com.switchfully.eurder.domain;

import java.time.LocalDate;
import java.util.UUID;

public class ItemGroup {

    private final UUID itemId;
    private final int amount;
    private final LocalDate shippingDate;
    private final double totalPrice;

    public ItemGroup(UUID itemId, int amount, LocalDate shippingDate, double totalPrice) {
        this.itemId = itemId;
        this.amount = amount;
        this.shippingDate = shippingDate;
        this.totalPrice = totalPrice;
    }

    public UUID getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

}
