package com.monika.hotelroombookingsystem;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {
    @Id
    private int customerId;
    
    private String customerName;
    
    public Customer()
    {}
    
    public Customer(int customerId,String customerName)
    {
        this.customerId=customerId;
        this.customerName=customerName;
    }
    
    public int getCustomerId()
    {
        return customerId;
    }
    
    public String getCustomerName()
    {
        return customerName;
    }
    
    public String toString()
    {
        return "Customer Id: "+customerId+
                ", Customer Name: "+customerName;
    }
}
