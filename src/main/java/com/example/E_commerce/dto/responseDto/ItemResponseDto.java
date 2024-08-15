package com.example.E_commerce.dto.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemResponseDto {
    int Quantity;
    String customerName;
    int priceOfOneItem;
    int totalPrice;
    String productName;

}
