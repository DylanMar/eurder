package com.switchfully.eurder.mapper;

import com.switchfully.eurder.dto.itemgroupdto.ItemGroupDto;
import com.switchfully.eurder.dto.orderdto.CreateOrderDto;
import com.switchfully.eurder.dto.orderdto.OrderDto;
import com.switchfully.eurder.dto.orderdto.PreviousOrderDto;
import com.switchfully.eurder.dto.orderdto.PreviousOrdersDto;
import com.switchfully.eurder.dto.userdto.UserDto;
import com.switchfully.eurder.entity.ItemGroup;
import com.switchfully.eurder.entity.Order;
import com.switchfully.eurder.entity.User;
import com.switchfully.eurder.repository.ItemGroupRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    private final ItemGroupRepository itemGroupRepository;
    private final ItemGroupMapper itemGroupMapper;
    private final UserMapper userMapper;

    public OrderMapper(ItemGroupRepository itemGroupRepository, ItemGroupMapper itemGroupMapper, UserMapper userMapper) {
        this.itemGroupRepository = itemGroupRepository;
        this.itemGroupMapper = itemGroupMapper;
        this.userMapper = userMapper;
    }

    public Order mapCreateOrderDtoToOrder(CreateOrderDto createOrderDto, User user) {
        List<ItemGroup> itemGroupList = createOrderDto
                .getCreateItemGroupDtoList()
                .stream()
                .map(itemGroupMapper::mapCreateItemGroupDtoToItemGroup)
                .toList();

        double totalPrice = itemGroupList
                .stream()
                .mapToDouble(ItemGroup::getTotalPrice)
                .sum();

        Order order = new Order(totalPrice, itemGroupList, user.getUserId());

        order
                .getItemGroups()
                .forEach((itemGroup) -> itemGroup.setOrder(order));

        return order;
    }

    public OrderDto mapOrderToOrderDto(Order order, User user) {
        itemGroupRepository.saveAll(order.getItemGroups());

        List<ItemGroupDto> itemGroupDtoList = order
                .getItemGroups()
                .stream()
                .map(itemGroupMapper::mapItemGroupToItemGroupDto)
                .toList();

        UserDto userDto = userMapper.mapUserToUserDto(user);

        return new OrderDto(
                order.getOrderId(),
                itemGroupDtoList,
                order.getTotalPrice(),
                userDto
        );
    }

    public PreviousOrderDto mapOrderToPreviousOrderDto(Order order) {
        List<ItemGroupDto> itemGroupDtoList = order
                .getItemGroups()
                .stream()
                .map(itemGroupMapper::mapItemGroupToItemGroupDto)
                .toList();

        return new PreviousOrderDto(
                order.getOrderId(),
                itemGroupDtoList,
                order.getTotalPrice()
        );
    }

    public PreviousOrdersDto mapToPreviousOrdersDto(List<PreviousOrderDto> previousOrderDtoList, double totalPrice) {
        return new PreviousOrdersDto(previousOrderDtoList, totalPrice);
    }

}
