package com.flightmanagement.SkyNest.controller;

import com.flightmanagement.SkyNest.model.User;
import com.flightmanagement.SkyNest.service.AuthenticationService;
import com.flightmanagement.SkyNest.util.JwtService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @Autowired
    public UserController(AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> userData) {
        try {
            String username = userData.get("username");
            String email = userData.get("email");
            String password = userData.get("password");
            String role = userData.get("role");

            if (username == null || email == null || password == null || role == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "All fields are required"));
            }

            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setRole(role);

            User registeredUser = authenticationService.signup(newUser);

            // Generate token directly from registered user
            String token = jwtService.generateToken(registeredUser);

            return ResponseEntity.ok().body(Map.of(
                    "message", "User registered successfully!",
                    "token", token,
                    "userId", registeredUser.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials,
            HttpServletResponse response) {
        try {
            String username = credentials.get("username");
            String password = credentials.get("password");

            if (username == null || password == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "Username and password are required"));
            }

            User authenticatedUser = authenticationService.login(username, password);
            String token = jwtService.generateToken(authenticatedUser);

            // Set JWT in cookie
            Cookie jwtCookie = new Cookie("JWT", token);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setSecure(false); // Set to true in production with HTTPS
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(7 * 24 * 60 * 60); // 1 week
            response.addCookie(jwtCookie);

            return ResponseEntity.ok().body(Map.of(
                    "message", "Login successful!",
                    "token", token,
                    "userId", authenticatedUser.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
        }
    }
}