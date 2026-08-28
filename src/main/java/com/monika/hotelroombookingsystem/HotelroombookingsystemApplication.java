package com.monika.hotelroombookingsystem;

import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelroombookingsystemApplication {

	public static void main(String[] args) {
		
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
            SpringApplication.run(HotelroombookingsystemApplication.class, args);
	}

}
