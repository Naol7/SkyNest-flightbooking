package com.flightmanagement.SkyNest.service;

import com.flightmanagement.SkyNest.model.Flight;
import com.flightmanagement.SkyNest.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    // Search for flights by origin and destination
    public List<Flight> searchFlights(String origin, String destination) {
        return flightRepository.findByOriginAndDestination(origin, destination);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    // Get flight details by ID
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
    }

    // Create a new flight (Admin only)
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    // Update flight details (Admin only)
    public Flight updateFlight(Long id, Flight flight) {
        Flight existingFlight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
        existingFlight.setFlightNumber(flight.getFlightNumber());
        existingFlight.setOrigin(flight.getOrigin());
        existingFlight.setDestination(flight.getDestination());
        existingFlight.setDepartureTime(flight.getDepartureTime());
        existingFlight.setArrivalTime(flight.getArrivalTime());
        existingFlight.setTotalSeats(flight.getTotalSeats());
        existingFlight.setAvailableSeats(flight.getAvailableSeats());
        existingFlight.setPrice(flight.getPrice());
        return flightRepository.save(existingFlight);
    }

    // Delete a flight (Admin only)
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    // Check seat availability
    public boolean isSeatAvailable(Long flightId, int seatCount) {
        Flight flight = getFlightById(flightId);
        return flight.getAvailableSeats() >= seatCount;
    }

    // Update available seats after booking
    public void updateAvailableSeats(Long flightId, int seatCount) {
        Flight flight = getFlightById(flightId);
        flight.setAvailableSeats(flight.getAvailableSeats() - seatCount);
        flightRepository.save(flight);
    }

}
