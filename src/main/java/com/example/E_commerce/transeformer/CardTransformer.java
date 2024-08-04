package com.example.E_commerce.transeformer;

import com.example.E_commerce.dto.requestDto.CardRequestDto;
import com.example.E_commerce.dto.responseDto.CardResponseDto;
import com.example.E_commerce.model.Card;

public class CardTransformer {
    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cvv(cardRequestDto.getCvv())
                .cardType(cardRequestDto.getCardType())
                .cardNo(cardRequestDto.getCardNo())
                .expiryDate(cardRequestDto.getExpiryDate())
                .build();
    }
    public static CardResponseDto CardToCardResponseDto(Card card){
        return CardResponseDto.builder()
                .CardNo(card.getCardNo())
                .CustomerName(card.getCustomer().getName())
                .build();
    }
}
