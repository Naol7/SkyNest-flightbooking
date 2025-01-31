package com.flightmanagement.SkyNest.service;

import com.flightmanagement.SkyNest.model.Booking;
import com.flightmanagement.SkyNest.model.Flight;
import com.flightmanagement.SkyNest.model.User;
import com.flightmanagement.SkyNest.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightService flightService;

    // Book a flight
    public Booking bookFlight(User user, Long flightId, int seatCount) {
        if (!flightService.isSeatAvailable(flightId, seatCount)) {
            throw new RuntimeException("Not enough seats available");
        }

        Flight flight = flightService.getFlightById(flightId);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setSeatCount(seatCount);
        booking.setBookingTime(LocalDateTime.now());

        flightService.updateAvailableSeats(flightId, seatCount);
        return bookingRepository.save(booking);
    }

    // Cancel a booking
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Flight flight = booking.getFlight();

        // Refund the seats to the flight
        flightService.updateAvailableSeats(flight.getId(), -booking.getSeatCount());

        // Delete the booking
        bookingRepository.delete(booking);
    }

    // Get all bookings for a user
    public List<Booking> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
}
