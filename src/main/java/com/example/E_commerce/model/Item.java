package com.example.E_commerce.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.antlr.v4.runtime.atn.SemanticContext;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "item")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int requiredQuantity;
     int price;

     @ManyToOne
    @JoinColumn
    Product product;

     @ManyToOne
    @JoinColumn
    Cart cart;

     @ManyToOne
    @JoinColumn
    Ordered ordered;



}
