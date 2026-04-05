package com.app.quantitymeasurement.config;

import com.app.quantitymeasurement.security.JwtFilter;
import com.app.quantitymeasurement.security.JwtUtil;
import com.app.quantitymeasurement.model.User;
import com.app.quantitymeasurement.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository repo;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth/**", "/h2-console/**", "/oauth2/**").permitAll()
                    .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))

            .oauth2Login(oauth -> oauth
                .successHandler((request, response, authentication) -> {

                    String email = authentication.getName();

                    // Save user if not exists
                    Optional<User> existingUser = repo.findByEmail(email);

                    if (existingUser.isEmpty()) {
                        User user = new User();
                        user.setEmail(email);
                        user.setName("Google User");
                        user.setPassword("");
                        user.setRole("USER");
                        repo.save(user);
                    }

                    // Generate JWT
                    String token = jwtUtil.generateToken(email);

                    response.setContentType("application/json");

                    response.getWriter().write(
                        "{ \"token\": \"" + token + "\", \"email\": \"" + email + "\" }"
                    );
                })
            )

            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}