package com.switchfully.eurder.dto;

import java.util.List;

public class OrderDto {

    private final Long orderId;
    private final List<ItemGroupDto> itemGroupDtoList;
    private final double totalPrice;
    private final UserDto userDto;

    public OrderDto(Long orderId, List<ItemGroupDto> itemGroupDtoList, double totalPrice, UserDto userDto) {
        this.orderId = orderId;
        this.itemGroupDtoList = itemGroupDtoList;
        this.totalPrice = totalPrice;
        this.userDto = userDto;
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

    public UserDto getCustomerDto() {
        return userDto;
    }

}
