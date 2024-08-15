package com.example.E_commerce.dto.responseDto;

import com.example.E_commerce.model.Customer;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartResponseDto {
    int cartTotal;
    int numberOfItem;
    String customerName;
    List<ItemResponseDto> itemResponseDtos;
}
