package com.app.measurement_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients    // ← Activates Feign so it can call user-service
public class MeasurementServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MeasurementServiceApplication.class, args);
    }
}
