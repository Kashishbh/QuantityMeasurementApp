package com.app.measurement_service.controller;

import com.app.measurement_service.dto.QuantityInputDTO;
import com.app.measurement_service.service.QuantityMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private QuantityMeasurementService service;

    // ===== COMPARE =====
    @PostMapping("/compare")
    public ResponseEntity<?> compare(
            @RequestBody QuantityInputDTO input,
            @RequestParam(required = false, defaultValue = "0") Long userId) {

        boolean result = service.compare(
                input.getThisQuantityDTO().getValue(),
                input.getThisQuantityDTO().getUnit(),
                input.getThatQuantityDTO().getValue(),
                input.getThatQuantityDTO().getUnit(),
                input.getMeasurementType(),
                userId
        );

        return ResponseEntity.ok(result);
    }

    // ===== ADD =====
    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestBody QuantityInputDTO input,
            @RequestParam(required = false, defaultValue = "0") Long userId) {

        double result = service.add(
                input.getThisQuantityDTO().getValue(),
                input.getThisQuantityDTO().getUnit(),
                input.getThatQuantityDTO().getValue(),
                input.getThatQuantityDTO().getUnit(),
                input.getMeasurementType(),
                userId
        );

        return ResponseEntity.ok(result);
    }

    // ===== SUBTRACT =====
    @PostMapping("/subtract")
    public ResponseEntity<?> subtract(
            @RequestBody QuantityInputDTO input,
            @RequestParam(required = false, defaultValue = "0") Long userId) {

        double result = service.subtract(
                input.getThisQuantityDTO().getValue(),
                input.getThisQuantityDTO().getUnit(),
                input.getThatQuantityDTO().getValue(),
                input.getThatQuantityDTO().getUnit(),
                input.getMeasurementType(),
                userId
        );

        return ResponseEntity.ok(result);
    }

    // ===== MULTIPLY =====
    @PostMapping("/multiply")
    public ResponseEntity<?> multiply(
            @RequestBody QuantityInputDTO input,
            @RequestParam(required = false, defaultValue = "0") Long userId) {

        double result = service.multiply(
                input.getThisQuantityDTO().getValue(),
                input.getThisQuantityDTO().getUnit(),
                input.getThatQuantityDTO().getValue(),
                input.getThatQuantityDTO().getUnit(),
                input.getMeasurementType(),
                userId
        );

        return ResponseEntity.ok(result);
    }

    // ===== DIVIDE =====
    @PostMapping("/divide")
    public ResponseEntity<?> divide(
            @RequestBody QuantityInputDTO input,
            @RequestParam(required = false, defaultValue = "0") Long userId) {

        double result = service.divide(
                input.getThisQuantityDTO().getValue(),
                input.getThisQuantityDTO().getUnit(),
                input.getThatQuantityDTO().getValue(),
                input.getThatQuantityDTO().getUnit(),
                input.getMeasurementType(),
                userId
        );

        return ResponseEntity.ok(result);
    }

    // ===== CONVERT =====
    @PostMapping("/convert")
    public ResponseEntity<?> convert(
            @RequestParam double value,
            @RequestParam String fromUnit,
            @RequestParam String toUnit,
            @RequestParam String measurementType,
            @RequestParam(required = false, defaultValue = "0") Long userId) {

        double result = service.convert(
                value,
                fromUnit,
                toUnit,
                measurementType,
                userId
        );

        return ResponseEntity.ok(result);
    }

    // ===== HISTORY =====
    @GetMapping("/history/{operation}")
    public ResponseEntity<?> getHistory(@PathVariable String operation) {
        return ResponseEntity.ok(service.getHistoryByOperation(operation));
    }

    // ===== COUNT =====
    @GetMapping("/count/{operation}")
    public ResponseEntity<?> getCount(@PathVariable String operation) {
        return ResponseEntity.ok(service.getOperationCount(operation));
    }
}