package com.app.user_service.security.oauth2;

import com.app.user_service.model.User;
import com.app.user_service.repository.UserRepository;
import com.app.user_service.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
public class OAuth2AuthenticationSuccessHandler
        implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;  // ← ADD THIS

    private static final String CALLBACK =
        "http://localhost:4200/oauth-callback";

    // ← ADD userRepository in constructor
    public OAuth2AuthenticationSuccessHandler(
            JwtUtil jwtUtil,
            UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        System.out.println("=== OAuth2AuthenticationSuccessHandler CREATED ===");
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {

        System.out.println("=== onAuthenticationSuccess CALLED ===");

        String email = null;
        String name = null;
        Object principal = authentication.getPrincipal();

        if (principal instanceof OAuth2User) {
            OAuth2User oauth2User = (OAuth2User) principal;
            email = oauth2User.getAttribute("email");
            name = oauth2User.getAttribute("name");
            System.out.println("=== EMAIL: " + email + " ===");
        }

        if (email == null || email.isBlank()) {
            email = authentication.getName();
            System.out.println("=== FALLBACK EMAIL: " + email + " ===");
        }

        
        Optional<User> existingUser = userRepository.findByEmail(email);
        System.out.println("=== USER EXISTS: " + existingUser.isPresent() + " ===");

        if (existingUser.isEmpty()) {
            User user = new User();
            user.setEmail(email);
            user.setName(name != null ? name : email);
            user.setRole("USER");
            user.setPassword("");
            User saved = userRepository.save(user);
            System.out.println("=== NEW USER SAVED: "
                + saved.getId() + " " + saved.getEmail() + " ===");
        } else {
            System.out.println("=== EXISTING USER: "
                + existingUser.get().getId() + " ===");
        }

        // JWT generate
        String token = jwtUtil.generateToken(email);

        String url = CALLBACK
            + "#token=" + URLEncoder.encode(token, StandardCharsets.UTF_8)
            + "&email=" + URLEncoder.encode(email, StandardCharsets.UTF_8);

        System.out.println("=== REDIRECTING TO 4200 ===");
        response.sendRedirect(url);
    }
}