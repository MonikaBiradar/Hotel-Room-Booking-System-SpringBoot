package com.monika.hotelroombookingsystem;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HotelService {
    
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final CustomerRepository customerRepository;
    
    public HotelService(RoomRepository roomRepository, CustomerRepository customerRepository, BookingRepository bookingRepository){
        this.roomRepository = roomRepository;
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
    }
    
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }
    
    public Room getRoom(int roomNum) {
        return roomRepository.findById(roomNum)
                .orElseThrow(()-> new ResourceNotFoundException("Room "+ roomNum + " not found"));
    }
    
    public void addRoom(Room room){
        if(!"Single".equalsIgnoreCase(room.getRoomType()) &&
        !"Double".equalsIgnoreCase(room.getRoomType()) &&
        !"Suite".equalsIgnoreCase(room.getRoomType())){
            throw new IllegalArgumentException(
                "Room type must be Single, Double or Suite");
        }

        
        roomRepository.save(room);
    }
    
    public void addCustomer(Customer customer){
        customerRepository.save(customer);
    }
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
    
    public Customer getCustomer(int customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer "+ customerId + " not found"));
    }
    
    
    public Booking bookRoom(int roomNum, int customerId, int totalNights) {
        if(totalNights <= 0){
            throw new IllegalArgumentException(
                      "Number of nights must be greater than 0");
        }
        
        Room room = getRoom(roomNum);
        
        Customer customer = getCustomer(customerId);
        
        for(Booking booking : bookingRepository.findAll()) {
            if (booking.getCustomer().getCustomerId() == customerId && 
                    booking.getRoom().getRoomNum() == roomNum &&
                    booking.getStatus() == BookingStatus.BOOKED){
                
                throw new BookingConflictException("Room "+ roomNum + " is already booked");
            }
        }

        if(!room.book())
            throw new BookingConflictException("Room "+ roomNum + " is already booked");
        
        Booking booking = new Booking(room, customer, totalNights);
        return bookingRepository.save(booking);
    }
    
    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }
    
    public void cancelBooking(int bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        
        if(booking == null)
            return;
        
        if (booking.getStatus() == BookingStatus.CANCELLED) 
            return;
        
        booking.getRoom().cancel();
        booking.cancel();
        
        bookingRepository.save(booking);
}
}
