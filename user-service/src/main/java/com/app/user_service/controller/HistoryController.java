package com.app.user_service.controller;

import com.app.user_service.dto.ConversionHistoryRequest;
import com.app.user_service.model.ConversionHistory;
import com.app.user_service.model.User;
import com.app.user_service.repository.HistoryRepository;
import com.app.user_service.repository.UserRepository;
import com.app.user_service.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class HistoryController {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // Called by measurement-service via Feign — no token needed
    @PostMapping("/{userId}/history")
    @ResponseStatus(HttpStatus.CREATED)
    public ConversionHistory saveHistory(
            @PathVariable Long userId,
            @RequestBody ConversionHistoryRequest request) {

        ConversionHistory history = new ConversionHistory();
        history.setUserId(userId);
        history.setMeasurementType(request.getMeasurementType());
        history.setThisUnit(request.getThisUnit());
        history.setThisValue(request.getThisValue());
        history.setThatUnit(request.getThatUnit());
        history.setThatValue(request.getThatValue());
        history.setResult(request.getResult());
        history.setTimestamp(LocalDateTime.now());

        return historyRepository.save(history);
    }

    //Called by frontend — token required
    // Frontend sends token, backend extracts email → finds userId → returns history
    @GetMapping("/my-history")
    public ResponseEntity<?> getMyHistory(
            @RequestHeader("Authorization") String authHeader) {

        // Step 1 — Extract token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Login required to view history");
        }

        String token = authHeader.substring(7);

        // Step 2 — Validate token
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(401).body("Invalid or expired token");
        }

        // Step 3 — Get email from token
        String email = jwtUtil.extractEmail(token);

        // Step 4 — Find user by email
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }

        // Step 5 — Get history for this user
        Long userId = (long) userOpt.get().getId();
        List<ConversionHistory> history = historyRepository.findByUserId(userId);

        return ResponseEntity.ok(history);
    }

    // Get userId from token — frontend needs this for operations
    @GetMapping("/me")
    public ResponseEntity<?> getMe(
            @RequestHeader("Authorization") String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Login required");
        }

        String token = authHeader.substring(7);

        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(401).body("Invalid or expired token");
        }

        String email = jwtUtil.extractEmail(token);

        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }

        User user = userOpt.get();

        return ResponseEntity.ok(java.util.Map.of(
            "userId", user.getId(),
            "email", user.getEmail(),
            "name", user.getName(),
            "role", user.getRole()
        ));
    }
}