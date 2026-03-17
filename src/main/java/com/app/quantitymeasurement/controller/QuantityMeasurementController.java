package com.app.quantitymeasurement.controller;

import java.util.*;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.service.QuantityMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private QuantityMeasurementService service;

    @PostMapping("/compare")
    public boolean compare(@RequestBody QuantityInputDTO input) {
        return service.compare(
                input.getThisQuantityDTO().getValue(),
                input.getThisQuantityDTO().getUnit(),
                input.getThatQuantityDTO().getValue(),
                input.getThatQuantityDTO().getUnit(),
                input.getMeasurementType()
        );
    }

    @PostMapping("/add")
    public double add(@RequestBody QuantityInputDTO input) {
        return service.add(
                input.getThisQuantityDTO().getValue(),
                input.getThisQuantityDTO().getUnit(),
                input.getThatQuantityDTO().getValue(),
                input.getThatQuantityDTO().getUnit(),
                input.getMeasurementType()
        );
    }

    @PostMapping("/subtract")
    public double subtract(@RequestBody QuantityInputDTO input) {
        return service.subtract(
                input.getThisQuantityDTO().getValue(),
                input.getThisQuantityDTO().getUnit(),
                input.getThatQuantityDTO().getValue(),
                input.getThatQuantityDTO().getUnit(),
                input.getMeasurementType()
        );
    }

    @PostMapping("/divide")
    public double divide(@RequestBody QuantityInputDTO input) {
        return service.divide(
                input.getThisQuantityDTO().getValue(),
                input.getThisQuantityDTO().getUnit(),
                input.getThatQuantityDTO().getValue(),
                input.getThatQuantityDTO().getUnit(),
                input.getMeasurementType()
        );
    }

    @PostMapping("/convert")
    public ResponseEntity<Double> convert(
            @RequestParam double value,
            @RequestParam String fromUnit,
            @RequestParam String toUnit,
            @RequestParam String measurementType) {

        double result = service.convert(value, fromUnit, toUnit, measurementType);
        return ResponseEntity.ok(result);
    } 

    @GetMapping("/history/{operation}")
    public List<QuantityMeasurementEntity> getHistory(@PathVariable String operation) {
        return service.getHistoryByOperation(operation);
    }    

    @GetMapping("/count/{operation}")
    public long getCount(@PathVariable String operation) {
        return service.getOperationCount(operation);
    }
}