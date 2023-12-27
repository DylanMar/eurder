package com.switchfully.eurder.dto;

import java.util.List;

public class CreateOrderDto {

    private  List<CreateItemGroupDto> createItemGroupDtoList;

    public CreateOrderDto() {
    }

    public List<CreateItemGroupDto> getCreateItemGroupDtoList() {
        return createItemGroupDtoList;
    }

    public void setCreateItemGroupDtoList(List<CreateItemGroupDto> createItemGroupDtoList) {
        this.createItemGroupDtoList = createItemGroupDtoList;
    }

}
