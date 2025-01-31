package com.flightmanagement.SkyNest.repository;

import com.flightmanagement.SkyNest.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Find all bookings by a user (using user ID for performance)
    List<Booking> findByUserId(Long userId);

    // Find all bookings for a specific flight (using flight ID)
    List<Booking> findByFlightId(Long flightId);

    // Optional: Find bookings by user and flight
    List<Booking> findByUserIdAndFlightId(Long userId, Long flightId);
}
