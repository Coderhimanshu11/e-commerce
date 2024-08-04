package com.example.E_commerce.service;

import com.example.E_commerce.Enum.CardType;
import com.example.E_commerce.Enum.Category;
import com.example.E_commerce.Exception.InvalidCustomerException;
import com.example.E_commerce.Exception.MobNoAlreadyPresentException;
import com.example.E_commerce.dto.requestDto.CustomerRequestDto;
import com.example.E_commerce.dto.responseDto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobNoAlreadyPresentException;
    public List<CustomerResponseDto> getAll();
    public List<CustomerResponseDto> getCustomerByEmailAndMobNo(String email,String mobNo) throws InvalidCustomerException;
    public List<CustomerResponseDto> getByAge(int age) throws InvalidCustomerException;

    public List<CustomerResponseDto> getBySpecificCard(CardType cardType) throws InvalidCustomerException;
   public CustomerResponseDto changeByEmail(String email ,CustomerRequestDto customerRequestDto) throws InvalidCustomerException;
   public CustomerResponseDto deleteByEmailOrMobNo(String email,String mobNo) throws InvalidCustomerException;
}
