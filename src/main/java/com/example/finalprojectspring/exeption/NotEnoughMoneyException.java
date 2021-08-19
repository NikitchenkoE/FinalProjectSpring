package com.example.finalprojectspring.exeption;

public class NotEnoughMoneyException extends Exception{

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
