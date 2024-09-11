package com.example.E_commerce.dto.requestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@Data
@Builder
public class OrderedRequestDto {
    int customerId;
    int productId;
    int requiredQuantity;
    String cardNo;
    int cvv;
}
