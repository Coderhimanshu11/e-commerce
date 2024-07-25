package com.example.E_commerce.service;

import com.example.E_commerce.Exception.EmailAlreadyPresentException;
import com.example.E_commerce.dto.requestDto.SellerRequestDto;
import com.example.E_commerce.dto.responseDto.SellerResponseDto;

public interface SellerService {
    public SellerResponseDto sellerAdd(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException;
}
