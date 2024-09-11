package com.example.E_commerce.controller;

import com.example.E_commerce.dto.requestDto.OrderedRequestDto;
import com.example.E_commerce.dto.responseDto.OrderedResponseDto;
import com.example.E_commerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderedController {
    @Autowired
    OrderService orderService;
@PostMapping("/place")
    public OrderedResponseDto placeDirectOrder(@RequestBody OrderedRequestDto orderedRequestDto) throws Exception {
  return orderService.placeOrder(orderedRequestDto);
}
}
