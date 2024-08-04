package com.example.E_commerce.controller;

import com.example.E_commerce.Exception.SellerNotFoundException;
import com.example.E_commerce.dto.requestDto.SellerRequestDto;
import com.example.E_commerce.dto.responseDto.SellerResponseDto;
import com.example.E_commerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto) {
        try {
            SellerResponseDto sellerResponseDto = sellerService.sellerAdd(sellerRequestDto);
            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_by_email")
    public SellerResponseDto getSellerByEmail(@RequestParam("emailId") String emailId) throws SellerNotFoundException {
        SellerResponseDto sellerResponseDto = sellerService.getSellerByEmail(emailId);
        return sellerResponseDto;
    }

    @GetMapping("/get_by_id")
    public SellerResponseDto getSellerById(@RequestParam("id") int id) throws SellerNotFoundException {
        SellerResponseDto sellerResponseDto = sellerService.getSellerById(id);
        return sellerResponseDto;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SellerResponseDto>> getAllSellers() {
        List<SellerResponseDto> sellerResponseDtos = sellerService.getAllSellers();
        return ResponseEntity.ok(sellerResponseDtos);
    }

    @PutMapping("/change_Seller_Info")
    public ResponseEntity<SellerResponseDto> changeByEmail(@RequestParam("emailId") String emailId, @RequestBody SellerRequestDto sellerRequestDto) {
        try {
            SellerResponseDto sellerResponseDto = sellerService.changeByEmail(emailId, sellerRequestDto);
            return ResponseEntity.ok(sellerResponseDto);
        } catch (SellerNotFoundException e) {
            return ResponseEntity.status(404).body(null);

        }
    }


    @DeleteMapping("/Delete-by-Email")
    public ResponseEntity<SellerResponseDto> deleteByEmailId(@RequestParam("emailId") String emailId) {
        try {
            sellerService.deleteByEmailId(emailId);
            // Return a successful response with HTTP 200 OK
            SellerResponseDto successResponse = new SellerResponseDto();
            return ResponseEntity.ok(successResponse);
        } catch (SellerNotFoundException e) {
            // Return an error response with HTTP 404 Not Found
            SellerResponseDto errorResponse = new SellerResponseDto();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
    @DeleteMapping("/Delete_By_Id/{id}")
    public ResponseEntity<SellerResponseDto> deleteById(@PathVariable("id")int id){
       try{
           SellerResponseDto sellerResponseDto=sellerService.deleteById(id);
           return ResponseEntity.ok(sellerResponseDto);
       } catch (SellerNotFoundException e) {
           SellerResponseDto errorNousResponse=new SellerResponseDto();
           errorNousResponse.setName("Seller with id"+id+"Not found");
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorNousResponse);
       }
    }
    @GetMapping("/getByAge")
    public ResponseEntity<List<SellerResponseDto>> getByAge(@RequestParam("age")int age) throws SellerNotFoundException{
 try{
     List<SellerResponseDto> sellers=sellerService.getByAge(age);
     return ResponseEntity.ok(sellers);
 }catch (SellerNotFoundException e){
  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
 }
    }




}

