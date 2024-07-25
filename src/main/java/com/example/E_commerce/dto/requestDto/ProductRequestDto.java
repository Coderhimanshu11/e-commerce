package com.example.E_commerce.dto.requestDto;

import com.example.E_commerce.Enum.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {
    int sellerId;

    String productName;
    int quantity;
    int price;

    Category category;


}
