package com.monika.hotelroombookingsystem;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelController {
    
    private final HotelService hotelService;
    
    public HotelController(HotelService hotelService){
        this.hotelService=hotelService;
    }
    
    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getAllRooms(){
        return ResponseEntity.ok(hotelService.getAllRooms());
    }
    
    @GetMapping("/rooms/{roomNum}")
    public ResponseEntity<Room> getRoom(@PathVariable int roomNum){
        Room room = hotelService.getRoom(roomNum);
        if(room == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(room);
    }
    
    @PostMapping("/rooms")
    public ResponseEntity<Room> addRoom(@Valid @RequestBody Room room){
        hotelService.addRoom(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }
    
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers(){
        return ResponseEntity.ok(hotelService.getAllCustomers());
    }
    
    @PostMapping("/customers")
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer){
        hotelService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
    
    @PostMapping("/bookings")
    public ResponseEntity<Booking> bookRoom(
            @RequestParam int roomNum,
            @RequestParam int customerId,
            @RequestParam int totalNights){
    
        Booking booking = hotelService.bookRoom(roomNum, customerId, totalNights);
        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }
    
    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(hotelService.getAllBookings());
    }
    
    @DeleteMapping("/bookings/{bookingId}")
    public ResponseEntity<Void> cancelBooking(@PathVariable int bookingId){
        hotelService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
}
