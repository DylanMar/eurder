package com.switchfully.eurder.service;

import com.switchfully.eurder.dto.UpdateItemDto;
import com.switchfully.eurder.entity.Item;
import com.switchfully.eurder.dto.CreateItemDto;
import com.switchfully.eurder.dto.ItemDto;
import com.switchfully.eurder.exception.NotFoundException;
import com.switchfully.eurder.mapper.ItemMapper;
import com.switchfully.eurder.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;


    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public ItemDto createItem(CreateItemDto createItemDto) {
        Item item = itemMapper.mapCreateItemDtoToItem(createItemDto);
        return itemMapper.mapItemToItemDto(itemRepository.save(item));
    }

    public ItemDto updateItem(Long id, UpdateItemDto updateItemDto) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new NotFoundException("Item id not found"));
        item.setName(updateItemDto.getName());
        item.setDescription(updateItemDto.getDescription());
        item.setPrice(updateItemDto.getPrice());
        item.setAmountInStock(updateItemDto.getAmountInStock());
        return itemMapper.mapItemToItemDto(item);
    }

}
