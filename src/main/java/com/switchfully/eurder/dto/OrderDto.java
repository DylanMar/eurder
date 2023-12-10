package com.switchfully.eurder.dto;

import java.time.LocalDate;
import java.util.List;

public class OrderDto {

    private final List<ItemGroupDto> itemGroupDtoList;
    private final double totalPrice;
    private final CustomerDto customerDto;

    public OrderDto(List<ItemGroupDto> itemGroupDtoList, double totalPrice, CustomerDto customerDto) {
        this.itemGroupDtoList = itemGroupDtoList;
        this.totalPrice = totalPrice;
        this.customerDto = customerDto;
    }

    public List<ItemGroupDto> getItemGroupDtoList() {
        return itemGroupDtoList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public CustomerDto getCustomerDto() {
        return this.customerDto;
    }

}
