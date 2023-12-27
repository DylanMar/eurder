package com.switchfully.eurder.controller;

import com.switchfully.eurder.dto.CreateItemDto;
import com.switchfully.eurder.dto.ItemDto;
import com.switchfully.eurder.dto.UpdateItemDto;
import com.switchfully.eurder.exception.AuthorizationException;
import com.switchfully.eurder.service.AuthorizationService;
import com.switchfully.eurder.service.ItemService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/item")
public class ItemController {

    private final AuthorizationService authorizationService;
    private final ItemService itemService;

    public ItemController(AuthorizationService authorizationService, ItemService itemService) {
        this.authorizationService = authorizationService;
        this.itemService = itemService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ItemDto createItem(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @RequestBody CreateItemDto createItemDto) {
        if (!authorizationService.isAdmin(authorization)) {
            throw new AuthorizationException("You are not authorized for this action");
        }
        return itemService.createItem(createItemDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ItemDto updateItem(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @RequestBody UpdateItemDto updateItemDto, @PathVariable Long id) {
        if (!authorizationService.isAdmin(authorization)) {
            throw new AuthorizationException("You are not authorized for this action");
        }
        return itemService.updateItem(id, updateItemDto);
    }

}
