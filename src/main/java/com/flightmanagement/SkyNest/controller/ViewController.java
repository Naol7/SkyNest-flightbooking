package com.flightmanagement.SkyNest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/myBookings")
    public String myBookingsPage() {
        return "myBookings";
    }
}
