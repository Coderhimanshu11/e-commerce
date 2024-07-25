package com.example.E_commerce.controller;

import com.example.E_commerce.Enum.Category;
import com.example.E_commerce.Exception.InvalidSellerException;
import com.example.E_commerce.dto.requestDto.ProductRequestDto;
import com.example.E_commerce.dto.responseDto.ProductResponseDto;
import com.example.E_commerce.service.ProductService;
import com.example.E_commerce.transeformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/add")
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) throws InvalidSellerException {
        return productService.addProduct(productRequestDto);
    }
    @GetMapping("/get/{category}")
    public List<ProductResponseDto> getProduct(@PathVariable("category")Category category){
        return productService.getProduct(category);
    }

}
