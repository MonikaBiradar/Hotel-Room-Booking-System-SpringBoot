package com.monika.hotelroombookingsystem;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Customer {
    
    @Id
    @Positive
    private int customerId;
    
    @NotBlank
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
