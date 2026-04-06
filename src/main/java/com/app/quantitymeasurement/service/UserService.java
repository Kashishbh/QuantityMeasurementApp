package com.app.quantitymeasurement.service;
import com.app.quantitymeasurement.dto.RegisterDTO;
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

    // ===== PASSWORD VALIDATION METHOD =====
    private void validatePassword(String password) {
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";

        if (!password.matches(pattern)) {
            throw new UserException(
                "Password must be at least 8 characters long and include uppercase, lowercase, number, and special character"
            );
        }
    }

    // ===== REGISTER =====
    public User register(RegisterDTO dto) {

        if (repo.findByEmail(dto.getEmail()).isPresent()) {
            throw new UserException("User already exists");
        }

        validatePassword(dto.getPassword());

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
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