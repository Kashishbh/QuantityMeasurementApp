package com.feet_measurement_equality.controller;

import com.feet_measurement_equality.dto.QuantityDTO;
import com.feet_measurement_equality.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private IQuantityMeasurementService service;
    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service=service;
    }

    public QuantityDTO performAddition(QuantityDTO q1, QuantityDTO q2) {
        return service.add(q1,q2);
    }

    public QuantityDTO performSubtraction(QuantityDTO q1, QuantityDTO q2) {
        return service.subtract(q1,q2);
    }

    public boolean performComparison(QuantityDTO q1, QuantityDTO q2) {
        return service.compare(q1,q2);
    }

    public double performDivision(QuantityDTO q1, QuantityDTO q2) {
    	try {
            double result = service.divide(q1, q2);
            System.out.println("Division Result: " + result);
        } 
        catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
		return 0;
    }
    public QuantityDTO performConversion(QuantityDTO source, String targetUnit) {
        return service.convert(source, targetUnit);
    }
}