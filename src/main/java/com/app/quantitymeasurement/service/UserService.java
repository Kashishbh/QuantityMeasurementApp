package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.model.User;
import com.app.quantitymeasurement.repository.UserRepository;
import com.app.quantitymeasurement.exception.UserException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ===== REGISTER =====
    public User register(User user) {

        // Check if user already exists
        if (repo.findByEmail(user.getEmail()).isPresent()) {
            throw new UserException("User already exists");
        }

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Default role
        user.setRole("USER");

        return repo.save(user);
    }

    // ===== LOGIN =====
    public User login(String email, String password) {

        User user = repo.findByEmail(email)
                .orElseThrow(() -> new UserException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UserException("Invalid password");
        }

        return user;
    }
}