package com.flightmanagement.SkyNest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {

    @GetMapping("/searchFlight")
    public String loginPage() {
        return "searchFlight"; // Renders searchFlight.html from templates
    }
}
