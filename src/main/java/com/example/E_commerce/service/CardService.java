package com.example.E_commerce.service;

import com.example.E_commerce.Exception.InvalidCustomerException;
import com.example.E_commerce.dto.requestDto.CardRequestDto;
import com.example.E_commerce.dto.responseDto.CardResponseDto;

public interface CardService {
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException;
}
