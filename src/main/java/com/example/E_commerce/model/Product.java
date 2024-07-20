package com.example.E_commerce.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "product")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
     int quantity;
     int price;
     int category;

     @ManyToOne
    @JoinColumn
    Seller seller;

     @OneToOne(mappedBy = "product",cascade = CascadeType.ALL)
    Item item;

}
