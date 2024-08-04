package com.example.E_commerce.dto.requestDto;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CustomerRequestDto {
    String name;


    String email;
    Integer age;
    String mobNo;
    String address;

}
