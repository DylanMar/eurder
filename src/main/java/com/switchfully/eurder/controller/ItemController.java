package com.switchfully.eurder.controller;

import com.switchfully.eurder.dto.itemdto.CreateItemDto;
import com.switchfully.eurder.dto.itemdto.ItemDto;
import com.switchfully.eurder.dto.itemdto.ItemResupplyUrgencyDto;
import com.switchfully.eurder.dto.itemdto.UpdateItemDto;
import com.switchfully.eurder.dto.itemgroupdto.ItemGroupDto;
import com.switchfully.eurder.exception.AuthorizationException;
import com.switchfully.eurder.service.AuthorizationService;
import com.switchfully.eurder.service.ItemService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/item")
public class ItemController {

    private final AuthorizationService authorizationService;
    private final ItemService itemService;

    public ItemController(AuthorizationService authorizationService, ItemService itemService) {
        this.authorizationService = authorizationService;
        this.itemService = itemService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/filter={filter}", produces = "application/json")
    public List<ItemResupplyUrgencyDto> getItems(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @PathVariable String filter) {
        if (!authorizationService.isAdmin(authorization)) {
            throw new AuthorizationException("You are not authorized for this action");
        }
        return itemService.getItems(filter);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json")
    public List<ItemGroupDto> getItemsShippingToday(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        if (!authorizationService.isAdmin(authorization)) {
            throw new AuthorizationException("You are not authorized for this action");
        }
        return itemService.getItemsShippingToday();
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
