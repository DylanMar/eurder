package com.switchfully.eurder.mapper;

import com.switchfully.eurder.dto.itemdto.CreateItemDto;
import com.switchfully.eurder.dto.itemdto.ItemDto;
import com.switchfully.eurder.dto.itemdto.ItemResupplyUrgencyDto;
import com.switchfully.eurder.dto.itemdto.ResupplyUrgency;
import com.switchfully.eurder.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {


    public Item mapCreateItemDtoToItem(CreateItemDto createItemDto) {
        return new Item(
                createItemDto.getName(),
                createItemDto.getDescription(),
                createItemDto.getPrice(),
                createItemDto.getAmountInStock()
        );
    }

    public ItemDto mapItemToItemDto(Item item) {
        return new ItemDto(
                item.getItemId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getAmountInStock()
        );
    }

    public ItemResupplyUrgencyDto mapItemToItemResupplyUrgencyDto(Item item) {
        ResupplyUrgency resupplyUrgency = ResupplyUrgency.STOCK_LOW;
        if (item.getAmountInStock() >= 10) {
            resupplyUrgency = ResupplyUrgency.STOCK_HIGH;
        } else if (item.getAmountInStock() >= 5) {
            resupplyUrgency = ResupplyUrgency.STOCK_MEDIUM;
        }

        return new ItemResupplyUrgencyDto(
                item.getItemId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                resupplyUrgency
        );
    }

}
