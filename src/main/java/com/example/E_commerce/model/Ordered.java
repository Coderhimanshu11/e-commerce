package com.example.E_commerce.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "ordered")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Ordered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String CardUsed;

    Date orderDate;

    int orderNo;
    int totalValue;

    @OneToOne(mappedBy = "ordered",cascade = CascadeType.ALL)
    Card card;

    @OneToMany(mappedBy = "ordered",cascade = CascadeType.ALL)
    List<Item> itemList=new ArrayList<>();

    @ManyToOne
    @JoinColumn
    Customer customer;
}
