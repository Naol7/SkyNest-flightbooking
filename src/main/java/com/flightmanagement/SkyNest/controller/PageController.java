package com.flightmanagement.SkyNest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/bookFlight")
    public String showBookFlightPage() {
        return "bookFlight"; // Refers to bookFlight.html in templates/
    }
}
