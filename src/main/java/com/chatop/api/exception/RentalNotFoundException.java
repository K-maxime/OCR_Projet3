package com.chatop.api.exception;

public class RentalNotFoundException extends RuntimeException{
    public RentalNotFoundException(){
        super("Rental not found");
    }
}
