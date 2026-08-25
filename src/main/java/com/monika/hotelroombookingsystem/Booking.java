package com.monika.hotelroombookingsystem;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    
    private int numOfNights;
    
    @ManyToOne
    private Room room;
    
    @ManyToOne
    private Customer customer;
    
    
    public Booking(){
    }
    
    public Booking(Room room,Customer customer,int numOfNights)
    {
        this.customer=customer;
        this.room=room;
        this.numOfNights=numOfNights;
        status=BookingStatus.BOOKED;
    }
    
    public int getBookingId(){return bookingId;}
    public Customer getCustomer(){return customer;}
    public Room getRoom(){return room;}
    public int getNumOfNights(){return numOfNights;}
    
    public BookingStatus getStatus()
    {
        return status;
    }
    
    public void cancel()
    {
        status=BookingStatus.CANCELLED;
    }
        
    public double getTotalPrice()
    {
        return (room.getRoomPrice()* numOfNights);
    }
    
    public String toString()
    {
        return "Booking Id: "+bookingId+
                ", Customer name: "+customer.getCustomerName()+
                ", Room No.: "+room.getRoomNum()+
                ", No. of Nights: "+numOfNights+
                ", Status: "+status;
    }
}
