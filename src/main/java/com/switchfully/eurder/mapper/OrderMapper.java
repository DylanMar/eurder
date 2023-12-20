package com.switchfully.eurder.mapper;

import com.switchfully.eurder.dto.CreateOrderDto;
import com.switchfully.eurder.dto.ItemGroupDto;
import com.switchfully.eurder.dto.OrderDto;
import com.switchfully.eurder.dto.UserDto;
import com.switchfully.eurder.entity.ItemGroup;
import com.switchfully.eurder.entity.Order;
import com.switchfully.eurder.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    private final ItemGroupMapper itemGroupMapper;
    private final UserMapper userMapper;


    public OrderMapper(ItemGroupMapper itemGroupMapper, UserMapper userMapper) {
        this.itemGroupMapper = itemGroupMapper;
        this.userMapper = userMapper;
    }

    public Order mapCreateOrderDtoToOrder(CreateOrderDto createOrderDto) {
        List<ItemGroup> itemGroupList = createOrderDto
                .getCreateItemGroupDtoList()
                .stream()
                .map(itemGroupMapper::mapCreateItemGroupDtoToItemGroup)
                .toList();

        double totalPrice = itemGroupList
                .stream()
                .mapToDouble(ItemGroup::getTotalPrice)
                .sum();

        User user = userMapper.mapCreateUserDtoToUser(createOrderDto.getCreateUserDto());

        return new Order(totalPrice, itemGroupList, user);
    }

    public OrderDto mapOrderToOrderDto(Order order) {
        List<ItemGroupDto> itemGroupDtoList = order
                .getItemGroups()
                .stream()
                .map(itemGroupMapper::mapItemGroupToItemGroupDto)
                .toList();

        UserDto userDto = userMapper.mapUserToUserDto(order.getUser());

        return new OrderDto(
                order.getOrderId(),
                itemGroupDtoList,
                order.getTotalPrice(),
                userDto
        );
    }

}
