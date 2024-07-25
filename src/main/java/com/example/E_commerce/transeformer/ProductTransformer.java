package com.example.E_commerce.transeformer;

import com.example.E_commerce.Enum.ProductStatus;
import com.example.E_commerce.dto.requestDto.ProductRequestDto;
import com.example.E_commerce.dto.responseDto.ProductResponseDto;
import com.example.E_commerce.model.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductTransformer {
    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .name(productRequestDto.getProductName())
                .productStatus(ProductStatus.AVAILABLE)
                .category(productRequestDto.getCategory())
               // .id(productRequestDto.getSellerId())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .build();
    }
    public static ProductResponseDto ProductToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .productName(product.getName())
                .sellerName(product.getSeller().getName())
                .quantity(product.getQuantity())
                .productStatus(product.getProductStatus())
                .build();
    }
}
