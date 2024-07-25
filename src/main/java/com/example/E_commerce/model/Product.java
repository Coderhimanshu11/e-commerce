package com.example.E_commerce.model;

import com.example.E_commerce.Enum.Category;
import com.example.E_commerce.Enum.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "product")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
     int quantity;
     int price;

     @Enumerated(EnumType.STRING)
     Category category;

     @Enumerated(EnumType.STRING)
     ProductStatus productStatus;

     @ManyToOne
    @JoinColumn(name = "seller_id")
    Seller seller;

     @OneToOne(mappedBy = "product",cascade = CascadeType.ALL)
    Item item;

}
