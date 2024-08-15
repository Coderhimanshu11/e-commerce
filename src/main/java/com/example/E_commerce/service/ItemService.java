package com.example.E_commerce.service;

import com.example.E_commerce.Exception.InvalidCustomerException;
import com.example.E_commerce.Exception.InvalidProductException;
import com.example.E_commerce.Exception.NoSufficientProductException;
import com.example.E_commerce.dto.requestDto.ItemRequestDto;
import com.example.E_commerce.model.Item;

public interface ItemService {
    public Item addItem(ItemRequestDto itemRequestDto) throws InvalidCustomerException, InvalidProductException, NoSufficientProductException;
}
