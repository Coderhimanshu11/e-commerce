package com.example.E_commerce.service;

import com.example.E_commerce.Exception.NoSufficientProductException;
import com.example.E_commerce.model.Card;
import com.example.E_commerce.model.Customer;
import com.example.E_commerce.model.Ordered;

public interface OrderService {
public Ordered placeOrder(Customer customer, Card card) throws NoSufficientProductException;
}
