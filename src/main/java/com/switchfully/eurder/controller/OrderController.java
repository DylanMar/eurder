package com.switchfully.eurder.controller;

import com.switchfully.eurder.dto.CreateOrderDto;
import com.switchfully.eurder.dto.OrderDto;
import com.switchfully.eurder.dto.PreviousOrdersDto;
import com.switchfully.eurder.exception.AuthorizationException;
import com.switchfully.eurder.service.AuthorizationService;
import com.switchfully.eurder.service.OrderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    private final AuthorizationService authorizationService;
    private final OrderService orderService;

    public OrderController(AuthorizationService authorizationService, OrderService orderService) {
        this.authorizationService = authorizationService;
        this.orderService = orderService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public OrderDto createOrder(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @RequestBody CreateOrderDto createOrderDto) {
        if (!authorizationService.isCustomer(authorization)) {
            throw new AuthorizationException("You are not authorized for this action");
        }
        return orderService.createOrder(createOrderDto, authorization);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json")
    public PreviousOrdersDto getAllOrders(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        if (!authorizationService.isCustomer(authorization)) {
            throw new AuthorizationException("You are not authorized for this action");
        }
        return orderService.getAllOrders(authorization);
    }

}
