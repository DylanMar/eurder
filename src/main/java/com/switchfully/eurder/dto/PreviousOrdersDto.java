package com.switchfully.eurder.dto;

import java.util.List;

public class PreviousOrdersDto {

    private List<PreviousOrderDto> previousOrderDtoList;
    private double totalPrice;

    public PreviousOrdersDto(List<PreviousOrderDto> previousOrderDtoList, double totalPrice) {
        this.previousOrderDtoList = previousOrderDtoList;
        this.totalPrice = totalPrice;
    }

    public List<PreviousOrderDto> getPreviousOrderDtoList() {
        return previousOrderDtoList;
    }

    public void setPreviousOrderDtoList(List<PreviousOrderDto> previousOrderDtoList) {
        this.previousOrderDtoList = previousOrderDtoList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
