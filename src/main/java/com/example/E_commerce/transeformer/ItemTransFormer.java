package com.example.E_commerce.transeformer;

import com.example.E_commerce.dto.requestDto.ItemRequestDto;
import com.example.E_commerce.model.Item;

public class ItemTransFormer {
    public static Item ItemRequestDtoToItem(ItemRequestDto itemRequestDto){
        return Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQuantity())
                .build();
    }
}
