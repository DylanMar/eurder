package com.switchfully.eurder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CreateOrderDto {

    private  List<CreateItemGroupDto> createItemGroupDtoList;
    private CreateUserDto createUserDto;

    public CreateOrderDto() {
    }

    public List<CreateItemGroupDto> getCreateItemGroupDtoList() {
        return createItemGroupDtoList;
    }

    public void setCreateItemGroupDtoList(List<CreateItemGroupDto> createItemGroupDtoList) {
        this.createItemGroupDtoList = createItemGroupDtoList;
    }

    public CreateUserDto getCreateUserDto() {
        return createUserDto;
    }

    public void setCreateUserDto(CreateUserDto createUserDto) {
        this.createUserDto = createUserDto;
    }

}
