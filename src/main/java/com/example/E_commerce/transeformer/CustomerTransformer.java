package com.example.E_commerce.transeformer;

import com.example.E_commerce.dto.requestDto.CustomerRequestDto;
import com.example.E_commerce.dto.responseDto.CustomerResponseDto;
import com.example.E_commerce.model.Customer;

public class CustomerTransformer {
    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .email(customerRequestDto.getEmail())
                .mobNo(customerRequestDto.getMobNo())
                .address(customerRequestDto.getAddress())
                .build();
    }
    public static CustomerResponseDto CustomerToCustomerResponseDto(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .message("Welcome " +customer.getName() +" to my company")
                .build();
    }
}
