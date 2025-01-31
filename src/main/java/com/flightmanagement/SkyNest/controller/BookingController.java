package com.flightmanagement.SkyNest.controller;

import com.flightmanagement.SkyNest.dto.BookingRequest;
import com.flightmanagement.SkyNest.model.Booking;
import com.flightmanagement.SkyNest.service.BookingService;
import com.flightmanagement.SkyNest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    // Book a flight
    @PostMapping("/book")
    public ResponseEntity<?> bookFlight(@RequestBody BookingRequest request) {
        try {
            Booking booking = bookingService.bookFlight(
                    userService.getUserById(request.getUserId()),
                    request.getFlightId(),
                    request.getSeatCount());
            return ResponseEntity.ok(Map.of("message", "Booking successful", "booking", booking));
        } catch (Exception e) {
            e.printStackTrace(); // Log the full stack trace
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    // Cancel a booking
    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long id) {
        try {
            bookingService.cancelBooking(id);
            return ResponseEntity.ok(Map.of("message", "Booking cancelled successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    // Get all bookings for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getBookingsForUser(@PathVariable Long userId) {
        try {
            List<Booking> bookings = bookingService.getUserBookings(userId);
            return ResponseEntity.ok(Map.of("bookings", bookings));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
}
