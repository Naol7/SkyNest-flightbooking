package com.flightmanagement.SkyNest.controller;

import com.flightmanagement.SkyNest.model.Flight;
import com.flightmanagement.SkyNest.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    // Get all flights (Available to all users)
    // Update getAllFlights method
    @GetMapping
    public ResponseEntity<?> getAllFlights() {
        try {
            List<Flight> flights = flightService.getAllFlights();
            return ResponseEntity.ok(flights);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    Map.of("error", "Failed to retrieve flights: " + e.getMessage()));
        }
    }

    // Search for flights (Available to all users)
    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam String origin,
            @RequestParam String destination) {
        List<Flight> flights = flightService.searchFlights(origin, destination);
        return ResponseEntity.ok(flights);
    }

    // Get flight details by ID (Available to all users)
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Flight flight = flightService.getFlightById(id);
        return ResponseEntity.ok(flight);
    }

    // Create a new flight
    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        Flight createdFlight = flightService.createFlight(flight);
        return ResponseEntity.ok(createdFlight);
    }

    // Update a flight
    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
        Flight updatedFlight = flightService.updateFlight(id, flight);
        return ResponseEntity.ok(updatedFlight);
    }

    // Delete a flight
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}