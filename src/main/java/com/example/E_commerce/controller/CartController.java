package com.example.E_commerce.controller;

import com.example.E_commerce.dto.requestDto.CheckoutCartRequestDto;
import com.example.E_commerce.dto.requestDto.ItemRequestDto;
import com.example.E_commerce.dto.responseDto.CartResponseDto;
import com.example.E_commerce.dto.responseDto.OrderedResponseDto;
import com.example.E_commerce.model.Item;
import com.example.E_commerce.service.CartService;
import com.example.E_commerce.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ItemService itemService;
    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto) {
        try {
            Item item = itemService.addItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.saveCart(itemRequestDto.getCustomerId(), item);
            return new ResponseEntity(cartResponseDto, HttpStatus.ACCEPTED);

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/checkout")
        public OrderedResponseDto checkoutCart(@RequestBody CheckoutCartRequestDto checkoutCartRequestDto) throws Exception {
return cartService.checkoutCart(checkoutCartRequestDto);
        }


}
