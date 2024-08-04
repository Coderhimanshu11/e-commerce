package com.example.E_commerce.dto.requestDto;

import com.example.E_commerce.Enum.CardType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardRequestDto {
    String mobNo;
    int cvv;
    String cardNo;
    Date expiryDate;

    CardType cardType;
}
