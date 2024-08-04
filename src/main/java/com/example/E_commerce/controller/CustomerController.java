package com.example.E_commerce.controller;

import com.example.E_commerce.Enum.CardType;
import com.example.E_commerce.Enum.Category;
import com.example.E_commerce.Exception.InvalidCustomerException;
import com.example.E_commerce.Exception.MobNoAlreadyPresentException;
import com.example.E_commerce.dto.requestDto.CustomerRequestDto;
import com.example.E_commerce.dto.responseDto.CustomerResponseDto;
import com.example.E_commerce.model.Customer;
import com.example.E_commerce.service.CustomerService;
import com.example.E_commerce.service.ServiceImpl.CustomerAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws MobNoAlreadyPresentException {
        return customerService.addCustomer(customerRequestDto);

    }

    @GetMapping("/getAll")
    public List<CustomerResponseDto> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/get/{email}/{mobNo}")
    public List<CustomerResponseDto> getCustomerByEmailAndMobNo(@PathVariable("email") String email, @PathVariable("mobNo") String mobNo) throws InvalidCustomerException {
        return customerService.getCustomerByEmailAndMobNo(email, mobNo);
    }

    @GetMapping("/get/age")
    public ResponseEntity<List<CustomerResponseDto>> getByAge(@RequestParam int age) throws InvalidCustomerException {
        try {
            List<CustomerResponseDto> customers = customerService.getByAge(age);
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (InvalidCustomerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/{cardType}")
    public ResponseEntity<List<CustomerResponseDto>> getBySpecificCard(@PathVariable("cardType") CardType cardType) {
        try {
            List<CustomerResponseDto> customerResponseDtos = customerService.getBySpecificCard(cardType);
            return new ResponseEntity<>(customerResponseDtos, HttpStatus.OK);
        } catch (InvalidCustomerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/change-customerInfo-byEmail")
    public CustomerResponseDto changeByEmail(@RequestParam("email") String email, @RequestBody CustomerRequestDto customerRequestDto) throws InvalidCustomerException {
        return customerService.changeByEmail(email, customerRequestDto);
    }

    @DeleteMapping("/delete-By-Email-Or-MobNo")
    public ResponseEntity<CustomerResponseDto> deleteByEmailOrMobNo(@RequestParam("email") String email, @RequestParam("mobNo") String mobNo) {
        try {
            CustomerResponseDto customerResponseDto = customerService.deleteByEmailOrMobNo(email, mobNo);
            return ResponseEntity.ok(customerResponseDto);
        } catch (InvalidCustomerException e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
}
