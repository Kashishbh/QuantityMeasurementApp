package com.app.quantitymeasurement.controller;
import com.app.quantitymeasurement.dto.RegisterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import com.app.quantitymeasurement.dto.LoginDTO;
import com.app.quantitymeasurement.model.User;
import com.app.quantitymeasurement.security.JwtUtil;
import com.app.quantitymeasurement.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtUtil jwtUtil;

    // ===== REGISTER =====
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO dto) {

        User savedUser = service.register(dto);

        return ResponseEntity.ok(Map.of(
                "message", "User registered successfully",
                "email", savedUser.getEmail()
        ));
    }
    // ===== LOGIN =====
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO request) {

        User user = service.login(request.getEmail(), request.getPassword());

        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(Map.of(
                "token", token,
                "email", user.getEmail(),
                "role", user.getRole()
        ));
    }
}