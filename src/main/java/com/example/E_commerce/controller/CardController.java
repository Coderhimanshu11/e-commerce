package com.example.E_commerce.controller;

import com.example.E_commerce.Enum.CardType;
import com.example.E_commerce.Exception.CardNotFoundException;
import com.example.E_commerce.Exception.InvalidCustomerException;
import com.example.E_commerce.Exception.SellerNotFoundException;
import com.example.E_commerce.dto.requestDto.CardRequestDto;
import com.example.E_commerce.dto.responseDto.CardResponseDto;
import com.example.E_commerce.dto.responseDto.CartResponseDto;
import com.example.E_commerce.dto.responseDto.SellerResponseDto;
import com.example.E_commerce.model.Card;
import com.example.E_commerce.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto) {
        try {
            CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto);
            return new ResponseEntity(cardResponseDto, HttpStatus.CREATED);
        } catch (InvalidCustomerException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
        @GetMapping("/get-specific-card/{cardType}")
        public ResponseEntity<List<CardResponseDto>> getSpecificCard(@PathVariable("cardType")CardType cardType){
            try{
                List<CardResponseDto> cardResponseDtos=cardService.getBySpecificCard(cardType);
                return new ResponseEntity<>(cardResponseDtos,HttpStatus.OK);
            }catch(CardNotFoundException e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
    }
    @GetMapping("/getCards/mastercard")
    public ResponseEntity<List<CardResponseDto>> getMasterCardsExpiringAfter(@RequestParam("expiryDate")
                                                                                 @DateTimeFormat(pattern = "yyyy-MM-dd")Date expiryDate){
        System.out.println("Received expiryDate: " + expiryDate);

        List<CardResponseDto> cardResponseDtos=cardService.getMasterCardExpiringAfter(expiryDate);
           return ResponseEntity.ok(cardResponseDtos);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<CardResponseDto> DeleteById(@PathVariable("id") int id){
        try{
            CardResponseDto cardResponseDto=cardService.DeleteById(id);
            return ResponseEntity.ok(cardResponseDto);
        } catch (CardNotFoundException e) {
            CardResponseDto errorNousResponse=new CardResponseDto();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorNousResponse);
        }

    }
//    @GetMapping("/maxcardtype")
//    public ResponseEntity<List<CardResponseDto>> getCardTypeWithMaxCount(){
//        List<CardResponseDto> card=cardService.getCardTypeWithMaxCount();
//        return ResponseEntity.ok(card);
//
//    }
}
