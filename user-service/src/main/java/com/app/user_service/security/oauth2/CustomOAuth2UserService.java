package com.app.user_service.security.oauth2;

import com.app.user_service.model.User;
import com.app.user_service.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        System.out.println("=== OAUTH2 LOAD USER EMAIL: " + email + " ===");

        if (email == null || email.isBlank()) {
            throw new RuntimeException(
                "Google did not return email. " +
                "Make sure 'email' scope is requested.");
        }

        Optional<User> existingUser = userRepository.findByEmail(email);
        System.out.println("=== USER EXISTS IN DB: "
            + existingUser.isPresent() + " ===");

        if (existingUser.isEmpty()) {
            User user = new User();
            user.setEmail(email);

            // ✅ null safe name
            String name = oAuth2User.getAttribute("name");
            user.setName(name != null ? name : email);

            user.setRole("USER");
            user.setPassword("");

            User saved = userRepository.save(user);
            System.out.println("=== NEW USER SAVED — ID: "
                + saved.getId()
                + " EMAIL: " + saved.getEmail() + " ===");
        } else {
            System.out.println("=== EXISTING USER FOUND — ID: "
                + existingUser.get().getId()
                + " EMAIL: " + existingUser.get().getEmail() + " ===");
        }

        return oAuth2User;
    }
}