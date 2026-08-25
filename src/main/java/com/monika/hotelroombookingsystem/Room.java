package com.monika.hotelroombookingsystem;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Room {
    @Id
    private int roomNum;
    
    private String roomType;
    private double roomPrice;
    private boolean isAvailable;
    
    public Room()
    {}
    
    public Room(int roomNum,String roomType){
        this.roomNum=roomNum;
        this.roomType=roomType;
        if("Single".equalsIgnoreCase(roomType)){roomPrice=1200;}
        if("Double".equalsIgnoreCase(roomType)){roomPrice=1800;}
        if("Suite".equalsIgnoreCase(roomType)){roomPrice=2400;}
        isAvailable=true;
    }
    
    public int getRoomNum(){
        return roomNum;
    }
    public String getRoomType(){
        return roomType;
    }
    public double getRoomPrice(){
        return roomPrice;
    }
    public boolean isAvailable(){
        return isAvailable;
    }
    
    public boolean book(){
        if(!isAvailable)
            return false;
        isAvailable=false;
        return true;
    }
    
    public boolean cancel(){
        if(isAvailable)
           return false;
        isAvailable=true;
        return true;
    }
    
    public String toString(){
        return "Room No.: "+roomNum+
                ", Room Type: "+roomType+
                ", Price/day: "+roomPrice+
                ", Availability: "+isAvailable;
    }
    
}
