package com.example.E_commerce.service;

import com.example.E_commerce.Enum.CardType;
import com.example.E_commerce.Exception.CardNotFoundException;
import com.example.E_commerce.Exception.InvalidCustomerException;
import com.example.E_commerce.dto.requestDto.CardRequestDto;
import com.example.E_commerce.dto.responseDto.CardResponseDto;
import com.example.E_commerce.model.Card;

import java.util.Date;
import java.util.List;

public interface CardService {
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException;
    public List<CardResponseDto> getBySpecificCard(CardType cardType) throws CardNotFoundException;
    public List<CardResponseDto> getMasterCardExpiringAfter(Date expiryDate);
public CardResponseDto DeleteById(int id) throws CardNotFoundException;
}
