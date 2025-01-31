package com.flightmanagement.SkyNest.service;

import com.flightmanagement.SkyNest.model.User;
import com.flightmanagement.SkyNest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register a new user with a selected role
    public User registerUser(String username, String email, String password, String role) {
        // Check if the username or email already exists
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // Encode the password before saving
        String encodedPassword = passwordEncoder.encode(password);

        // Assign the selected role (must start with "ROLE_")
        String assignedRole = role.equalsIgnoreCase("ROLE_ADMIN") ? "ROLE_ADMIN" : "ROLE_USER";

        // Create and save the new user
        User newUser = new User(username, email, encodedPassword, assignedRole);
        return userRepository.save(newUser);
    }

    // Authenticate a user (for login)
    public User authenticateUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Invalid username or password");
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        return user;
    }

    // Get user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
