package com.monika.hotelroombookingsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class HotelServiceTest {
   
    @Mock
    private RoomRepository roomRepository;
   
    @Mock
    private CustomerRepository customerRepository;
    
    @Mock
    private BookingRepository bookingRepository;
   
    private HotelService hotelService;
    private AutoCloseable mocks;
    
    @BeforeEach
    void setUp(){
        
        mocks = MockitoAnnotations.openMocks(this);
        
        hotelService = new HotelService(
            roomRepository,
            customerRepository,
            bookingRepository);
    }
    
    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }
    
    @Test
    void getRoom_whenRoomExists_returnRoom(){
        Room room = new Room(102, "Double");
        
        when(roomRepository.findById(102))
                .thenReturn(Optional.of(room));
        
        Room result = hotelService.getRoom(102);
        
        assertEquals(room, result);
    }
    
    @Test
    void getRoom_whenRoomDoesNotExist_throwsException(){
        when(roomRepository.findById(999))
                .thenReturn(Optional.empty());
        
        assertThrows(ResourceNotFoundException.class,()-> hotelService.getRoom(999));
    }
    
    @Test
    void addRoom_whenRoomTypeisValid_savesRoom(){
        Room room = new Room(104, "Single");
        
        hotelService.addRoom(room);
        
        verify(roomRepository).save(room);
    }
    
    @Test
    void addRoom_whenRoomTypeIsInvalid_throwsException(){
        Room room = new Room(105, "Deluxe");
        
        assertThrows(IllegalArgumentException.class, ()-> hotelService.addRoom(room));
        
        verify(roomRepository, never()).save(room);
    }
    
    @Test
    void bookRoom_whenRoomAndCustomerAreValid_createsBooking() {
        Room room = new Room(104, "Single");
        when(roomRepository.findById(104))
                .thenReturn(Optional.of(room));
        
        Customer customer = new Customer(5, "Alice");
        when(customerRepository.findById(5))
                .thenReturn(Optional.of(customer));
        
        when(bookingRepository.findByRoomRoomNumAndStatus(104, BookingStatus.BOOKED))
                .thenReturn(Optional.empty());
        
        hotelService.bookRoom(104, 5, 2);
        
        ArgumentCaptor<Booking> bookingCaptor = ArgumentCaptor.forClass(Booking.class);
        
        verify(bookingRepository).save(bookingCaptor.capture());
        
        Booking savedBooking = bookingCaptor.getValue();
        
        assertEquals(room, savedBooking.getRoom());
        assertEquals(customer, savedBooking.getCustomer());
        assertEquals(2, savedBooking.getNumOfNights());
    }
    
    @Test
    void bookRoom_whenRoomIsAlreadyBooked_throwsConflictException() {

        Room room = new Room(104, "Single");

        when(roomRepository.findById(104))
                .thenReturn(Optional.of(room));

        Customer customer = new Customer(5, "Alice");

        when(customerRepository.findById(5))
                .thenReturn(Optional.of(customer));

        Booking existingBooking =
                new Booking(room, customer, 2);

        when(bookingRepository.findByRoomRoomNumAndStatus(
                104, BookingStatus.BOOKED))
                .thenReturn(Optional.of(existingBooking));

        assertThrows(BookingConflictException.class, () -> hotelService.bookRoom(104, 5, 2));

        verify(bookingRepository, never()).save(org.mockito.ArgumentMatchers.any(Booking.class));
    }
    
    @Test
    void bookRoom_whenRoomDoesNotExist_throwsNotFoundException() {

        when(roomRepository.findById(999))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> hotelService.bookRoom(999, 5, 2));
    }
    
    @Test
    void bookRoom_whenCustomerDoesNotExist_throwsNotFoundException() {

        Room room = new Room(104, "Single");

        when(roomRepository.findById(104))
                .thenReturn(Optional.of(room));

        when(customerRepository.findById(999))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> hotelService.bookRoom(104, 999, 2));
    }
    
    @Test
    void bookRoom_whenNightsAreInvalid_throwsException() {

        assertThrows(IllegalArgumentException.class, () -> hotelService.bookRoom(104, 5, 0));

        verify(roomRepository, never()).findById(104);
    }
    
    @Test
    void cancelBooking_whenBookingExists_cancelsBooking() {

        Room room = new Room(104, "Single");
        room.book();

        Customer customer = new Customer(5, "Alice");
        Booking booking = new Booking(room, customer, 2);

        when(bookingRepository.findById(1))
                .thenReturn(Optional.of(booking));

        hotelService.cancelBooking(1);

        assertEquals(BookingStatus.CANCELLED, booking.getStatus());
        assertEquals(true, room.isAvailable());

        verify(bookingRepository).save(booking);
    }
    
    @Test
    void cancelBooking_whenBookingDoesNotExist_throwsNotFoundException() {

        when(bookingRepository.findById(999))
                .thenReturn(Optional.empty());

        assertThrows( ResourceNotFoundException.class, () -> hotelService.cancelBooking(999));
    }
    
    @Test
    void cancelBooking_whenAlreadyCancelled_throwsConflictException() {

        Room room = new Room(104, "Single");
        room.book();

        Customer customer = new Customer(5, "Alice");
        Booking booking = new Booking(room, customer, 2);

        booking.cancel();

        when(bookingRepository.findById(5))
                .thenReturn(Optional.of(booking));

        assertThrows(BookingConflictException.class, () -> hotelService.cancelBooking(5));

        verify(bookingRepository, never()).save(booking);
    }
}
