package com.switchfully.eurder.dto;

import java.util.List;

public class PreviousOrderDto {

    private final Long orderId;
    private final List<ItemGroupDto> itemGroupDtoList;
    private final double totalPrice;

    public PreviousOrderDto(Long orderId, List<ItemGroupDto> itemGroupDtoList, double totalPrice) {
        this.orderId = orderId;
        this.itemGroupDtoList = itemGroupDtoList;
        this.totalPrice = totalPrice;
    }

    public Long getOrderId() {
        return orderId;
    }

    public List<ItemGroupDto> getItemGroupDtoList() {
        return itemGroupDtoList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

}
