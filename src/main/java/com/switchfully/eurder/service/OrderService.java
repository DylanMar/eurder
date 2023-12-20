package com.switchfully.eurder.service;

import com.switchfully.eurder.dto.CreateOrderDto;
import com.switchfully.eurder.dto.OrderDto;
import com.switchfully.eurder.entity.Order;
import com.switchfully.eurder.mapper.OrderMapper;
import com.switchfully.eurder.repository.OrderRepository;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public OrderDto createOrder(CreateOrderDto createOrderDto) {
        Order order = orderMapper.mapCreateOrderDtoToOrder(createOrderDto);
        return orderMapper.mapOrderToOrderDto(orderRepository.save(order));
    }

}
