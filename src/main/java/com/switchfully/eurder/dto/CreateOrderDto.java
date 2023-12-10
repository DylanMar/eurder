package com.switchfully.eurder.dto;

import java.util.List;

public class CreateOrderDto {

    private  List<CreateItemGroupDto> createItemGroupDtoList;

    public CreateOrderDto() {
        // Jackson doesn't work if you don't do it with a default constructor and setters.
        // No idea why
        // Yeeeeey black boxed frameworks
    }

    public void setCreateItemGroupDtoList(List<CreateItemGroupDto> createItemGroupDtoList) {
        this.createItemGroupDtoList = createItemGroupDtoList;
    }

    public List<CreateItemGroupDto> getCreateItemGroupDtoList() {
        return createItemGroupDtoList;
    }

}
