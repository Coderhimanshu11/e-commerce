package com.example.E_commerce.dto.responseDto;

import com.example.E_commerce.model.Card;
import com.example.E_commerce.model.Customer;
import com.example.E_commerce.model.Item;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderedResponseDto {
    String CardUsed;


    Date orderDate;

    String orderNo;
    int totalValue;


    List<ItemResponseDto> items;


    String  customerName;
}
