//package com.switchfully.eurder.service;
//
//import com.switchfully.eurder.entity.Item;
//import com.switchfully.eurder.dto.CreateItemDto;
//import com.switchfully.eurder.dto.ItemDto;
//import com.switchfully.eurder.repository.ItemRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class ItemServiceTest {
//
//    private ItemService itemService;
//
//    private ItemRepository itemRepository;
//
//    private ItemMapper itemMapper;
//
//    @BeforeEach
//    void setUp() {
//        itemRepository = mock(ItemRepository.class);
//        itemMapper = mock(ItemMapper.class);
//        itemService = new ItemService(itemRepository, itemMapper);
//    }
//
//    @Test
//    void createItem_ValidCreateItemDto_ReturnsItemDto() {
//        // Arrange
//        CreateItemDto createItemDto = new CreateItemDto("TestItem", "Description", 15.0, 5);
//        Item item = new Item("TestItem", "Description", 15.0, 5);
//        ItemDto expectedItemDto = new ItemDto(item.getId(), "TestItem", "Description", 15.0, 5);
//
//        when(itemMapper.mapCreateItemDtoToItem(createItemDto)).thenReturn(item);
//        when(itemRepository.addItem(item)).thenReturn(item);
//        when(itemMapper.MapItemToItemDto(item)).thenReturn(expectedItemDto);
//
//        // Act
//        ItemDto createdItemDto = itemService.createItem(createItemDto);
//
//        // Assert
//        assertNotNull(createdItemDto);
//        assertEquals(expectedItemDto, createdItemDto);
//
//        // Verify
//        verify(itemMapper, times(1)).mapCreateItemDtoToItem(createItemDto);
//        verify(itemRepository, times(1)).addItem(item);
//        verify(itemMapper, times(1)).MapItemToItemDto(item);
//    }
//
//    @Test
//    void createItem_InvalidCreateItemDto_ThrowsIllegalArgumentException() {
//        // Arrange
//        CreateItemDto invalidCreateItemDto = new CreateItemDto(null, "Description", 15.0, 5);
//
//        when(itemMapper.mapCreateItemDtoToItem(invalidCreateItemDto)).thenThrow(new IllegalArgumentException("Invalid input"));
//
//        // Act and Assert
//        assertThrows(IllegalArgumentException.class, () -> itemService.createItem(invalidCreateItemDto));
//
//        // Verify
//        verify(itemMapper, times(1)).mapCreateItemDtoToItem(invalidCreateItemDto);
//        verify(itemRepository, never()).addItem(any());
//        verify(itemMapper, never()).MapItemToItemDto(any());
//    }
//}
