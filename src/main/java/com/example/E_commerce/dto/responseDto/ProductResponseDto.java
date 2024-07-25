package com.example.E_commerce.dto.responseDto;

import com.example.E_commerce.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {
    String productName;
    String sellerName;
    int quantity;

    ProductStatus productStatus;
}
