package com.example.E_commerce.service;

import com.example.E_commerce.Exception.EmailAlreadyPresentException;
import com.example.E_commerce.Exception.SellerNotFoundException;
import com.example.E_commerce.dto.requestDto.SellerRequestDto;
import com.example.E_commerce.dto.responseDto.SellerResponseDto;
import com.example.E_commerce.model.Seller;

import java.util.List;

public interface SellerService {
    public SellerResponseDto sellerAdd(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException;
    public SellerResponseDto getSellerByEmail(String emailId) throws SellerNotFoundException;
    public SellerResponseDto getSellerById(int id) throws SellerNotFoundException;
    public List<SellerResponseDto> getAllSellers();
    public SellerResponseDto changeByEmail(String emailId, SellerRequestDto sellerRequestDto) throws SellerNotFoundException;
    public SellerResponseDto deleteByEmailId(String emailId) throws SellerNotFoundException;
    public SellerResponseDto deleteById(int id) throws SellerNotFoundException;
    public List<SellerResponseDto> getByAge(int age)throws SellerNotFoundException;
}
