package com.app.quantitymeasurement.service;

import java.util.*;

import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import com.app.quantitymeasurement.model.OperationType;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;
import com.app.quantitymeasurement.exception.QuantityMeasurementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repo;

    // ===== LENGTH BASE UNIT = INCH =====
    private double toInch(double value, String unit) {
        switch(unit.toUpperCase()) {
            case "INCH": return value;
            case "FEET": return value * 12;
            case "YARD": return value * 36;
            case "METER": return value * 39.37;
            default: throw new QuantityMeasurementException("Invalid Length Unit");
        }
    }

    private double fromInch(double value, String unit) {
        switch(unit.toUpperCase()) {
            case "INCH": return value;
            case "FEET": return value / 12;
            case "YARD": return value / 36;
            case "METER": return value / 39.37;
            default: throw new QuantityMeasurementException("Invalid Length Unit");
        }
    }

    // ===== VOLUME =====
    private double toLitre(double value, String unit) {
        switch(unit.toUpperCase()) {
            case "LITRE": return value;
            case "MILLILITRE": return value * 0.001;
            case "GALLON": return value * 3.78541;
            default: throw new QuantityMeasurementException("Invalid Volume Unit");
        }
    }

    private double fromLitre(double value, String unit) {
        switch(unit.toUpperCase()) {
            case "LITRE": return value;
            case "MILLILITRE": return value / 0.001;
            case "GALLON": return value / 3.78541;
            default: throw new QuantityMeasurementException("Invalid Volume Unit");
        }
    }

    // ===== WEIGHT =====
    private double toKilogram(double value, String unit) {
        switch(unit.toUpperCase()) {
            case "KILOGRAM": return value;
            case "GRAM": return value * 0.001;
            case "POUND": return value * 0.453592;
            default: throw new QuantityMeasurementException("Invalid Weight Unit");
        }
    }

    private double fromKilogram(double value, String unit) {
        switch(unit.toUpperCase()) {
            case "KILOGRAM": return value;
            case "GRAM": return value / 0.001;
            case "POUND": return value / 0.453592;
            default: throw new QuantityMeasurementException("Invalid Weight Unit");
        }
    }

    // ===== TEMPERATURE =====
    private double toCelsius(double value, String unit) {
        switch(unit.toUpperCase()) {
            case "CELSIUS": return value;
            case "FAHRENHEIT": return (value - 32) * 5/9;
            default: throw new QuantityMeasurementException("Invalid Temperature Unit");
        }
    }

    private double fromCelsius(double value, String unit) {
        switch(unit.toUpperCase()) {
            case "CELSIUS": return value;
            case "FAHRENHEIT": return (value * 9/5) + 32;
            default: throw new QuantityMeasurementException("Invalid Temperature Unit");
        }
    }

    // ===== COMPARE =====
    public boolean compare(double v1, String u1, double v2, String u2, String measurementType) {

        double val1, val2;

        switch(measurementType.toUpperCase()) {
            case "LENGTH": val1 = toInch(v1, u1); val2 = toInch(v2, u2); break;
            case "VOLUME": val1 = toLitre(v1, u1); val2 = toLitre(v2, u2); break;
            case "WEIGHT": val1 = toKilogram(v1, u1); val2 = toKilogram(v2, u2); break;
            case "TEMPERATURE": val1 = toCelsius(v1, u1); val2 = toCelsius(v2, u2); break;
            default: throw new QuantityMeasurementException("Unknown Measurement Type");
        }

        boolean result = Math.abs(val1 - val2) < 0.0001;

        repo.save(new QuantityMeasurementEntity(
                v1, u1, v2, u2,
                OperationType.COMPARE,
                result ? 1 : 0,
                ""
        ));

        return result;
    }

    // ===== ADD =====
    public double add(double v1, String u1, double v2, String u2, String measurementType) {

        double val1, val2, sum;
        String resultUnit = u1;

        switch(measurementType.toUpperCase()) {
            case "LENGTH":
                val1 = toInch(v1, u1); val2 = toInch(v2, u2);
                sum = fromInch(val1 + val2, u1);
                break;
            case "VOLUME":
                val1 = toLitre(v1, u1); val2 = toLitre(v2, u2);
                sum = fromLitre(val1 + val2, u1);
                break;
            case "WEIGHT":
                val1 = toKilogram(v1, u1); val2 = toKilogram(v2, u2);
                sum = fromKilogram(val1 + val2, u1);
                break;
            case "TEMPERATURE":
                throw new QuantityMeasurementException("Addition not supported for Temperature");
            default:
                throw new QuantityMeasurementException("Unsupported Measurement Type for Add");
        }

        repo.save(new QuantityMeasurementEntity(
                v1, u1, v2, u2,
                OperationType.ADD,
                sum,
                resultUnit
        ));

        return sum;
    }

    // ===== SUBTRACT =====
    public double subtract(double v1, String u1, double v2, String u2, String measurementType) {

        double val1, val2, diff;
        String resultUnit = u1;

        switch(measurementType.toUpperCase()) {
            case "LENGTH":
                val1 = toInch(v1, u1); val2 = toInch(v2, u2);
                diff = fromInch(val1 - val2, u1);
                break;
            case "VOLUME":
                val1 = toLitre(v1, u1); val2 = toLitre(v2, u2);
                diff = fromLitre(val1 - val2, u1);
                break;
            case "WEIGHT":
                val1 = toKilogram(v1, u1); val2 = toKilogram(v2, u2);
                diff = fromKilogram(val1 - val2, u1);
                break;
            case "TEMPERATURE":
                throw new QuantityMeasurementException("Subtraction not supported for Temperature");
            default:
                throw new QuantityMeasurementException("Unsupported Measurement Type for Subtract");
        }

        repo.save(new QuantityMeasurementEntity(
                v1, u1, v2, u2,
                OperationType.SUBTRACT,
                diff,
                resultUnit
        ));

        return diff;
    }
    
    public double multiply(double v1, String u1, double v2, String u2, String measurementType) {

        double val1, val2, result;

        switch(measurementType.toUpperCase()) {
            case "LENGTH":
                val1 = toInch(v1, u1);
                val2 = toInch(v2, u2);
                break;

            case "VOLUME":
                val1 = toLitre(v1, u1);
                val2 = toLitre(v2, u2);
                break;

            case "WEIGHT":
                val1 = toKilogram(v1, u1);
                val2 = toKilogram(v2, u2);
                break;

            case "TEMPERATURE":
                throw new QuantityMeasurementException("Multiplication not supported for Temperature");

            default:
                throw new QuantityMeasurementException("Unsupported Measurement Type for Multiply");
        }

        result = val1 * val2;

        repo.save(new QuantityMeasurementEntity(
                v1, u1,
                v2, u2,
                OperationType.MULTIPLY,
                result,
                ""
        ));

        return result;
    }
    

    // ===== DIVIDE =====
    public double divide(double v1, String u1, double v2, String u2, String measurementType) {

        double val1, val2, result;

        switch(measurementType.toUpperCase()) {
            case "LENGTH": val1 = toInch(v1, u1); val2 = toInch(v2, u2); break;
            case "VOLUME": val1 = toLitre(v1, u1); val2 = toLitre(v2, u2); break;
            case "WEIGHT": val1 = toKilogram(v1, u1); val2 = toKilogram(v2, u2); break;
            case "TEMPERATURE":
                throw new QuantityMeasurementException("Division not supported for Temperature");
            default:
                throw new QuantityMeasurementException("Unsupported Measurement Type for Divide");
        }

        if(val2 == 0) throw new QuantityMeasurementException("Divide by zero");

        result = val1 / val2;

        repo.save(new QuantityMeasurementEntity(
                v1, u1, v2, u2,
                OperationType.DIVIDE,
                result,
                ""
        ));

        return result;
    }

    // ===== CONVERT =====
    public double convert(double v, String from, String to, String measurementType) {

        double base, result;

        switch(measurementType.toUpperCase()) {
            case "LENGTH": base = toInch(v, from); result = fromInch(base, to); break;
            case "VOLUME": base = toLitre(v, from); result = fromLitre(base, to); break;
            case "WEIGHT": base = toKilogram(v, from); result = fromKilogram(base, to); break;
            case "TEMPERATURE": base = toCelsius(v, from); result = fromCelsius(base, to); break;
            default: throw new QuantityMeasurementException("Unknown Measurement Type");
        }

        repo.save(new QuantityMeasurementEntity(
                v, from, 0, "",
                OperationType.CONVERT,
                result,
                to
        ));

        return result;
    }

    // ===== HISTORY =====
    public List<QuantityMeasurementEntity> getHistoryByOperation(String operation) {
        return repo.findByOperation(OperationType.valueOf(operation.toUpperCase()));
    }

    public long getOperationCount(String operation) {
        return repo.countByOperation(OperationType.valueOf(operation.toUpperCase()));
    }
}