package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.ItemGroup;
import com.switchfully.eurder.domain.Order;
import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.dto.CreateOrderDto;
import com.switchfully.eurder.dto.OrderDto;
import com.switchfully.eurder.mapper.OrderMapper;
import com.switchfully.eurder.repository.ItemRepository;
import com.switchfully.eurder.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private static final int SHIPPING_DATE_WAITING_TIME_IN_STOCK = 1;
    private static final int SHIPPING_DATE_WAITING_TIME_NOT_IN_STOCK = 7;

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.orderMapper = orderMapper;
    }

    public OrderDto createOrder(CreateOrderDto createOrderDto, User user) {
        try {
            List<ItemGroup> itemGroupList = createOrderDto.getCreateItemGroupDtoList()
                    .stream()
                    .map(createItemGroupDto ->  orderMapper.mapCreateItemGroupDtoToItemGroup(createItemGroupDto,
                            calculateShippingDate(createItemGroupDto.getItemId(), createItemGroupDto.getAmount()),
                            calculateTotalPrice(createItemGroupDto.getItemId(), createItemGroupDto.getAmount())))
                    .toList();

            Order order = orderMapper.mapCreateOrderDtoToOrder(itemGroupList, user);
            Order addedOrder = orderRepository.addOrder(order);

            return orderMapper.mapOrderToOrderDto(addedOrder);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public LocalDate calculateShippingDate(UUID itemId, int amount) {
        Item item = itemRepository.getItemById(itemId);
        if (item.getAmountInStock() > amount) {
            return LocalDate.now().plusDays(SHIPPING_DATE_WAITING_TIME_IN_STOCK);
        } else {
            return  LocalDate.now().plusDays(SHIPPING_DATE_WAITING_TIME_NOT_IN_STOCK);
        }
    }

    private double calculateTotalPrice(UUID itemId, int amount) {
        System.out.println(itemRepository.getItemById(itemId).getPrice() * amount);
        return itemRepository.getItemById(itemId).getPrice() * amount;
    }

}
