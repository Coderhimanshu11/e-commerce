package com.example.E_commerce.controller;

import com.example.E_commerce.Exception.InvalidCustomerException;
import com.example.E_commerce.dto.requestDto.CardRequestDto;
import com.example.E_commerce.dto.responseDto.CardResponseDto;
import com.example.E_commerce.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){
        try{
            CardResponseDto cardResponseDto=cardService.addCard(cardRequestDto);
            return new ResponseEntity(cardResponseDto, HttpStatus.CREATED);
        }
         catch (InvalidCustomerException e) {
        return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);

        }
    }
}
