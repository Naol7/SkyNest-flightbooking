package com.flightmanagement.SkyNest.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class AdminController {

    @GetMapping("/manageFlights")
    public String manageFlightsPage() {
        return "manageFlights";
    }
}
