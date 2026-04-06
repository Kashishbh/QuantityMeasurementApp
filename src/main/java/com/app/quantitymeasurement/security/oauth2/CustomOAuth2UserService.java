package com.app.quantitymeasurement.security.oauth2;

import com.app.quantitymeasurement.model.User;
import com.app.quantitymeasurement.repository.UserRepository;
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
        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isEmpty()) {
            User user = new User();
            user.setEmail(email);
            user.setName(oAuth2User.getAttribute("name"));
            user.setRole("USER");
            user.setPassword(""); // OAuth users have no password
            userRepository.save(user);
        }

        return oAuth2User;
    }
}