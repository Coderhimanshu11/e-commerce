package com.example.E_commerce.dto.requestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SellerRequestDto {
    String name;


    String emailId;

    Integer age;
    String mob_No;

}
