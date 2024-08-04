package com.example.E_commerce.Exception;

import jakarta.persistence.criteria.CriteriaBuilder;

public class InvalidCustomerException extends Exception{
    public InvalidCustomerException(String message){
        super(message);
    }
}
