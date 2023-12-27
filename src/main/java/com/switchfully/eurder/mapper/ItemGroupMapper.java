package com.switchfully.eurder.mapper;

import com.switchfully.eurder.dto.CreateItemGroupDto;
import com.switchfully.eurder.dto.ItemGroupDto;
import com.switchfully.eurder.entity.Item;
import com.switchfully.eurder.entity.ItemGroup;
import com.switchfully.eurder.entity.Order;
import com.switchfully.eurder.exception.NotFoundException;
import com.switchfully.eurder.repository.ItemRepository;
import com.switchfully.eurder.repository.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class ItemGroupMapper {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    public ItemGroupMapper(ItemRepository itemRepository, OrderRepository orderRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }

    public ItemGroup mapCreateItemGroupDtoToItemGroup(CreateItemGroupDto createItemGroupDto) {
        Item item = itemRepository.findById(createItemGroupDto.getItemId())
                .orElseThrow(() -> new NotFoundException("Item id not found"));
        return new ItemGroup(item, createItemGroupDto.getAmount());
    }

    public ItemGroupDto mapItemGroupToItemGroupDto(ItemGroup itemGroup) {
        return new ItemGroupDto(
                itemGroup.getItemGroupId(),
                itemGroup.getAmount(),
                itemGroup.getShippingDate(),
                itemGroup.getTotalPrice()
        );
    }

}
