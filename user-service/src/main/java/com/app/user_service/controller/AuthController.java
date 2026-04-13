package com.app.user_service.controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.user_service.model.User;
import com.app.user_service.dto.LoginDTO;
import com.app.user_service.dto.RegisterDTO;
import com.app.user_service.security.JwtUtil;
import com.app.user_service.service.UserService;


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