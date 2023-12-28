package com.switchfully.eurder.service;

import com.switchfully.eurder.dto.itemdto.CreateItemDto;
import com.switchfully.eurder.dto.itemdto.ItemDto;
import com.switchfully.eurder.dto.itemdto.ItemResupplyUrgencyDto;
import com.switchfully.eurder.dto.itemdto.UpdateItemDto;
import com.switchfully.eurder.dto.itemgroupdto.ItemGroupDto;
import com.switchfully.eurder.entity.Item;
import com.switchfully.eurder.entity.ItemGroup;
import com.switchfully.eurder.exception.NotFoundException;
import com.switchfully.eurder.mapper.ItemGroupMapper;
import com.switchfully.eurder.mapper.ItemMapper;
import com.switchfully.eurder.repository.ItemGroupRepository;
import com.switchfully.eurder.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemGroupRepository itemGroupRepository;
    private final ItemMapper itemMapper;
    private final ItemGroupMapper itemGroupMapper;


    public ItemService(ItemRepository itemRepository, ItemGroupRepository itemGroupRepository, ItemMapper itemMapper, ItemGroupMapper itemGroupMapper) {
        this.itemRepository = itemRepository;
        this.itemGroupRepository = itemGroupRepository;
        this.itemMapper = itemMapper;
        this.itemGroupMapper = itemGroupMapper;
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

    public List<ItemResupplyUrgencyDto> getItems(String filter) {
        List<ItemResupplyUrgencyDto> items = itemRepository
                .findAll()
                .stream()
                .map(itemMapper::mapItemToItemResupplyUrgencyDto)
                .toList();
        if (!filter.trim().isEmpty()) {
            items = items
                    .stream()
                    .filter(itemResupplyUrgencyDto -> itemResupplyUrgencyDto.getResupplyUrgency().toString().equals(filter))
                    .toList();

        }
        return items;
    }

    public List<ItemGroupDto> getItemsShippingToday() {
        List<ItemGroup> itemGroups = itemGroupRepository
                .findAll()
                .stream()
                .filter(itemGroup -> itemGroup.getShippingDate().equals(LocalDate.now()))
                .toList();
        return itemGroups
                .stream()
                .map(itemGroupMapper::mapItemGroupToItemGroupDto)
                .toList();
    }

}
