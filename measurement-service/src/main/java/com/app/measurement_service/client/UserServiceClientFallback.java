package com.app.measurement_service.client;

import com.app.measurement_service.dto.ConversionHistoryRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserServiceClientFallback implements UserServiceClient {

    private static final Logger log =
            LoggerFactory.getLogger(UserServiceClientFallback.class);

    @Override
    public void saveHistory(Long userId, ConversionHistoryRequest request) {
        log.warn("user-service is DOWN — history not saved for user {}", userId);
    }
}