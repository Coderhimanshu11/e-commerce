package com.example.E_commerce.service;

import com.example.E_commerce.Exception.InvalidCardException;
import com.example.E_commerce.Exception.InvalidCustomerException;
import com.example.E_commerce.Exception.NoSufficientCartException;
import com.example.E_commerce.dto.requestDto.CheckoutCartRequestDto;
import com.example.E_commerce.dto.responseDto.CartResponseDto;
import com.example.E_commerce.dto.responseDto.OrderedResponseDto;
import com.example.E_commerce.model.Item;
import jakarta.mail.FetchProfile;

public interface CartService {
    public CartResponseDto saveCart(int customerId, Item item);
    public OrderedResponseDto checkoutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws Exception;

}
