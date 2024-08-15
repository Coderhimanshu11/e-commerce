package com.example.E_commerce.Exception;

import org.aspectj.weaver.ast.Not;

public class NoSufficientProductException extends Exception{
    public NoSufficientProductException(String message){
        super(message);
    }
}
