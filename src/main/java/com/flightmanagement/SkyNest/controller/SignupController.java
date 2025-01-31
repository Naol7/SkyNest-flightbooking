package com.flightmanagement.SkyNest.controller;

import com.flightmanagement.SkyNest.model.User;
import com.flightmanagement.SkyNest.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup"; // Maps to src/main/resources/templates/signup.html
    }

    @PostMapping("/signup")
    public String processSignup(
        @RequestParam("username") String username,
        @RequestParam("email") String email,
        @RequestParam("password") String password,
        @RequestParam("role") String role,
        Model model
    ) {
        try {
            userService.registerUser(username, email, password, role);
            return "redirect:/login"; // Redirect to login after successful signup
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "signup"; // Reload signup page with error message
        }
    }
}
