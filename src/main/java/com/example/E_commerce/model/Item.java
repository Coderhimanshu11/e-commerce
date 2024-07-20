package com.example.E_commerce.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.antlr.v4.runtime.atn.SemanticContext;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "item")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    int requiredQuantity;
     int cost;

     @OneToOne
    @JoinColumn
    Product product;

     @ManyToOne
    @JoinColumn
    Cart cart;

     @ManyToOne
    @JoinColumn
    Ordered ordered;



}
