package com.switchfully.eurder.mapper;

import com.switchfully.eurder.entity.ItemGroup;
import com.switchfully.eurder.entity.Order;
import com.switchfully.eurder.entity.User;
import com.switchfully.eurder.dto.CreateItemGroupDto;
import com.switchfully.eurder.dto.ItemGroupDto;
import com.switchfully.eurder.dto.OrderDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class OrderMapper {

    private final UserMapper userMapper;

    public OrderMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Order mapCreateOrderDtoToOrder(List<ItemGroup> itemGroupList, User user) {
        return new Order(itemGroupList, user);
    }

    public OrderDto mapOrderToOrderDto(Order order) {
        List<ItemGroupDto> itemGroupDtoList = order.getItemGroups()
                .stream()
                .map(this::mapItemGroupToItemGroupDto)
                .toList();
        return new OrderDto(itemGroupDtoList, order.getTotalPrice(), userMapper.mapUserToCustomerDto(order.getCustomer()));
    }


    public ItemGroup mapCreateItemGroupDtoToItemGroup(CreateItemGroupDto createItemGroupDto, LocalDate shippingDate, double totalPrice) {
        return new ItemGroup(createItemGroupDto.getItemId(), createItemGroupDto.getAmount(), shippingDate, totalPrice);
    }

    public ItemGroupDto mapItemGroupToItemGroupDto(ItemGroup itemGroup) {
        return new ItemGroupDto(itemGroup.getItemId(),
                itemGroup.getAmount(),
                itemGroup.getShippingDate(),
                itemGroup.getTotalPrice());
    }

}
