package com.app.measurement_service.client;

import com.app.measurement_service.dto.ConversionHistoryRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service", fallback = UserServiceClientFallback.class)
public interface UserServiceClient {   // ✅ INTERFACE (not class)

    @PostMapping("/api/users/{userId}/history")
    void saveHistory(
            @PathVariable("userId") Long userId,
            @RequestBody ConversionHistoryRequest request
    );
}