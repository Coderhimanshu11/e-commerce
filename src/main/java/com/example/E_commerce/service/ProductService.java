package com.example.E_commerce.service;

import com.example.E_commerce.Enum.Category;
import com.example.E_commerce.Exception.InvalidSellerException;
import com.example.E_commerce.Exception.ProductNotFoundException;
import com.example.E_commerce.dto.requestDto.ProductRequestDto;
import com.example.E_commerce.dto.responseDto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException;
    public List<ProductResponseDto> getProduct(Category category);
    public List<ProductResponseDto> getBySellerEmailId(String emailId) throws InvalidSellerException;
   // public ProductResponseDto deleteBySellerAndProductId(int sellerId,int productId) throws ProductNotFoundException;
    public List<ProductResponseDto> getProductByPriceAndCategory(Integer price,String category);
}
