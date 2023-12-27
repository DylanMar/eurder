package com.switchfully.eurder.service;

import com.switchfully.eurder.dto.CreateOrderDto;
import com.switchfully.eurder.dto.OrderDto;
import com.switchfully.eurder.dto.PreviousOrderDto;
import com.switchfully.eurder.dto.PreviousOrdersDto;
import com.switchfully.eurder.entity.Order;
import com.switchfully.eurder.entity.User;
import com.switchfully.eurder.mapper.OrderMapper;
import com.switchfully.eurder.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final AuthorizationService authorizationService;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, AuthorizationService authorizationService, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.authorizationService = authorizationService;
        this.orderMapper = orderMapper;
    }

    public OrderDto createOrder(CreateOrderDto createOrderDto, String authorization) {
        User user = authorizationService.getUser(authorization);
        Order order = orderMapper.mapCreateOrderDtoToOrder(createOrderDto, user);
        return orderMapper.mapOrderToOrderDto(orderRepository.save(order), user);
    }

    public PreviousOrdersDto getAllOrders(String authorization) {
        User user = authorizationService.getUser(authorization);
        List<Order> orders = orderRepository.findByUser(user.getUserId());

        List<PreviousOrderDto> previousOrderDtoList = orders
                .stream()
                .map(orderMapper::mapOrderToPreviousOrderDto)
                .toList();

        double totalPrice = orders
                .stream()
                .mapToDouble(Order::getTotalPrice)
                .sum();

        return orderMapper.mapToPreviousOrdersDto(previousOrderDtoList, totalPrice);
    }

}
