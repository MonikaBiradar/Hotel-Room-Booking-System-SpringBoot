package com.monika.hotelroombookingsystem;

import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HotelControllerTest {

    @Mock
    private HotelService hotelService;

    private MockMvc mockMvc;
    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {

        mocks = MockitoAnnotations.openMocks(this);

        HotelController hotelController =
                new HotelController(hotelService);

        mockMvc = MockMvcBuilders
                .standaloneSetup(hotelController)
                .build();
    }
    
    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void getAllRooms_returnsRooms() throws Exception {

        Room room1 = new Room(101, "Single");
        Room room2 = new Room(102, "Double");

        when(hotelService.getAllRooms())
                .thenReturn(Arrays.asList(room1, room2));

        mockMvc.perform(get("/rooms"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].roomNum").value(101))
                .andExpect(jsonPath("$[1].roomNum").value(102));
    }

    @Test
    void getRoom_returnsRoom() throws Exception {

        Room room = new Room(102, "Double");

        when(hotelService.getRoom(102))
                .thenReturn(room);

        mockMvc.perform(get("/rooms/102"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomNum").value(102))
                .andExpect(jsonPath("$.roomType").value("Double"))
                .andExpect(jsonPath("$.roomPrice").value(1800.0))
                .andExpect(jsonPath("$.available").value(true));
    }

    @Test
    void addRoom_returnsCreated() throws Exception {

        String requestBody = """
                {
                    "roomNum": 103,
                    "roomType": "Single"
                }
                """;

        mockMvc.perform(post("/rooms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.roomNum").value(103))
                .andExpect(jsonPath("$.roomType").value("Single"));

        verify(hotelService).addRoom(any(Room.class));
    }

    @Test
    void getCustomers_returnsCustomers() throws Exception {

        Customer customer1 = new Customer(1, "Tom");
        Customer customer2 = new Customer(2, "Smith");

        when(hotelService.getAllCustomers())
                .thenReturn(Arrays.asList(customer1, customer2));

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].customerId").value(1))
                .andExpect(jsonPath("$[1].customerId").value(2));
    }

    @Test
    void addCustomer_returnsCreated() throws Exception {

        String requestBody = """
                {
                    "customerId": 6,
                    "customerName": "Alice"
                }
                """;

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerId").value(6))
                .andExpect(jsonPath("$.customerName").value("Alice"));

        verify(hotelService).addCustomer(any(Customer.class));
    }

    @Test
    void bookRoom_returnsCreated() throws Exception {

        Room room = new Room(102, "Double");
        Customer customer = new Customer(4, "John");
        Booking booking = new Booking(room, customer, 2);

        when(hotelService.bookRoom(102, 4, 2))
                .thenReturn(booking);

        mockMvc.perform(post("/bookings")
                .param("roomNum", "102")
                .param("customerId", "4")
                .param("totalNights", "2"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.room.roomNum").value(102))
                .andExpect(jsonPath("$.customer.customerId").value(4))
                .andExpect(jsonPath("$.numOfNights").value(2));

        verify(hotelService).bookRoom(102, 4, 2);
    }

    @Test
    void cancelBooking_returnsNoContent() throws Exception {

        mockMvc.perform(delete("/bookings/5"))
                .andExpect(status().isNoContent());

        verify(hotelService).cancelBooking(5);
    }
}