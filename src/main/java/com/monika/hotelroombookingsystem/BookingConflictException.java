package com.monika.hotelroombookingsystem;

public class BookingConflictException extends RuntimeException {
    
    public BookingConflictException(String message){
        super(message);
    }
}
