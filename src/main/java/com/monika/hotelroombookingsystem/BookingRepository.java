package com.monika.hotelroombookingsystem;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Optional<Booking> findByRoomRoomNumAndStatus(int roomNum, BookingStatus status);
}
