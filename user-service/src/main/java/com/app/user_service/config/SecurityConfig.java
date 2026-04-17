package com.app.user_service.config;

import com.app.user_service.security.JwtFilter;
import com.app.user_service.security.oauth2.CustomOAuth2UserService;
import com.app.user_service.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.app.user_service.security.oauth2.OAuth2AuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler successHandler;
    private final OAuth2AuthenticationFailureHandler failureHandler;

    // Constructor injection 
    public SecurityConfig(
            JwtFilter jwtFilter,
            CustomOAuth2UserService customOAuth2UserService,
            OAuth2AuthenticationSuccessHandler successHandler,
            OAuth2AuthenticationFailureHandler failureHandler) {
        this.jwtFilter = jwtFilter;
        this.customOAuth2UserService = customOAuth2UserService;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;

        System.out.println("=== SecurityConfig LOADED ===");
        System.out.println("=== successHandler: "
            + successHandler.getClass().getName() + " ===");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {

        System.out.println("=== filterChain BUILDING ===");

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/oauth2/**").permitAll()
                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/api/users/*/history").permitAll()
                .requestMatchers("/api/users/me").permitAll()
                .requestMatchers("/api/users/my-history").permitAll()
                .requestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
                    ).permitAll()

                .anyRequest().authenticated()
            )
            .oauth2Login(oauth -> oauth
                .userInfoEndpoint(userInfo -> userInfo
                    .userService(customOAuth2UserService))
                .successHandler(successHandler)
                .failureHandler(failureHandler)
            )
            .addFilterBefore(jwtFilter,
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}