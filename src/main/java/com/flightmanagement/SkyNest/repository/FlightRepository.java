package com.flightmanagement.SkyNest.repository;
import com.flightmanagement.SkyNest.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;


public interface FlightRepository extends JpaRepository<Flight, Long> {
    // Find flights by origin and destination
    List<Flight> findByOriginAndDestination(String origin, String destination);

    // Find flights by flight number
    Optional<Flight> findByFlightNumber(String flightNumber);
}
