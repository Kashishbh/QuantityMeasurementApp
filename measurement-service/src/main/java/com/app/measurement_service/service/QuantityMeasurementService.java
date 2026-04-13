package com.app.measurement_service.service;

import com.app.measurement_service.client.UserServiceClient;
import com.app.measurement_service.dto.ConversionHistoryRequest;
import com.app.measurement_service.exception.QuantityMeasurementException;
import com.app.measurement_service.model.OperationType;
import com.app.measurement_service.model.QuantityMeasurementEntity;
import com.app.measurement_service.repository.QuantityMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repo;

    @Autowired
    private UserServiceClient userServiceClient;

    // ===== LENGTH — base unit: INCH =====
    private double toInch(double value, String unit) {
        switch (unit.toUpperCase()) {
            case "INCH":  return value;
            case "FEET":  return value * 12;
            case "YARD":  return value * 36;
            case "METER": return value * 39.37;
            default: throw new QuantityMeasurementException("Invalid Length Unit: " + unit);
        }
    }

    private double fromInch(double value, String unit) {
        switch (unit.toUpperCase()) {
            case "INCH":  return value;
            case "FEET":  return value / 12;
            case "YARD":  return value / 36;
            case "METER": return value / 39.37;
            default: throw new QuantityMeasurementException("Invalid Length Unit: " + unit);
        }
    }

    // ===== VOLUME — base unit: LITRE =====
    private double toLitre(double value, String unit) {
        switch (unit.toUpperCase()) {
            case "LITRE":      return value;
            case "MILLILITRE": return value * 0.001;
            case "GALLON":     return value * 3.78541;
            default: throw new QuantityMeasurementException("Invalid Volume Unit: " + unit);
        }
    }

    private double fromLitre(double value, String unit) {
        switch (unit.toUpperCase()) {
            case "LITRE":      return value;
            case "MILLILITRE": return value / 0.001;
            case "GALLON":     return value / 3.78541;
            default: throw new QuantityMeasurementException("Invalid Volume Unit: " + unit);
        }
    }

    // ===== WEIGHT — base unit: KILOGRAM =====
    private double toKilogram(double value, String unit) {
        switch (unit.toUpperCase()) {
            case "KILOGRAM": return value;
            case "GRAM":     return value * 0.001;
            case "POUND":    return value * 0.453592;
            default: throw new QuantityMeasurementException("Invalid Weight Unit: " + unit);
        }
    }

    private double fromKilogram(double value, String unit) {
        switch (unit.toUpperCase()) {
            case "KILOGRAM": return value;
            case "GRAM":     return value / 0.001;
            case "POUND":    return value / 0.453592;
            default: throw new QuantityMeasurementException("Invalid Weight Unit: " + unit);
        }
    }

    // ===== TEMPERATURE — base unit: CELSIUS =====
    private double toCelsius(double value, String unit) {
        switch (unit.toUpperCase()) {
            case "CELSIUS":    return value;
            case "FAHRENHEIT": return (value - 32) * 5 / 9;
            default: throw new QuantityMeasurementException("Invalid Temperature Unit: " + unit);
        }
    }

    private double fromCelsius(double value, String unit) {
        switch (unit.toUpperCase()) {
            case "CELSIUS":    return value;
            case "FAHRENHEIT": return (value * 9 / 5) + 32;
            default: throw new QuantityMeasurementException("Invalid Temperature Unit: " + unit);
        }
    }

    // ===== COMPARE =====
    public boolean compare(double v1, String u1, double v2, String u2,
                           String measurementType, Long userId) {

        double val1, val2;

        switch (measurementType.toUpperCase()) {
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
                val1 = toCelsius(v1, u1);
                val2 = toCelsius(v2, u2);
                break;
            default:
                throw new QuantityMeasurementException("Unknown Measurement Type: " + measurementType);
        }

        boolean result = Math.abs(val1 - val2) < 0.0001;

        repo.save(new QuantityMeasurementEntity(
                v1, u1, v2, u2,
                OperationType.COMPARE,
                result ? 1 : 0,
                ""
        ));

        if (userId != null && userId > 0) {
            userServiceClient.saveHistory(userId,
                new ConversionHistoryRequest(measurementType, u1, v1, u2, v2, result ? 1 : 0));
        }

        return result;
    }

    // ===== ADD =====
    public double add(double v1, String u1, double v2, String u2,
                      String measurementType, Long userId) {

        double val1, val2, sum;

        switch (measurementType.toUpperCase()) {
            case "LENGTH":
                val1 = toInch(v1, u1);
                val2 = toInch(v2, u2);
                sum  = fromInch(val1 + val2, u1);
                break;
            case "VOLUME":
                val1 = toLitre(v1, u1);
                val2 = toLitre(v2, u2);
                sum  = fromLitre(val1 + val2, u1);
                break;
            case "WEIGHT":
                val1 = toKilogram(v1, u1);
                val2 = toKilogram(v2, u2);
                sum  = fromKilogram(val1 + val2, u1);
                break;
            case "TEMPERATURE":
                throw new QuantityMeasurementException("Addition not supported for Temperature");
            default:
                throw new QuantityMeasurementException("Unknown Measurement Type: " + measurementType);
        }

        repo.save(new QuantityMeasurementEntity(
                v1, u1, v2, u2,
                OperationType.ADD,
                sum, u1
        ));

        if (userId != null && userId > 0) {
            userServiceClient.saveHistory(userId,
                new ConversionHistoryRequest(measurementType, u1, v1, u2, v2, sum));
        }

        return sum;
    }

    // ===== SUBTRACT =====
    public double subtract(double v1, String u1, double v2, String u2,
                           String measurementType, Long userId) {

        double val1, val2, diff;

        switch (measurementType.toUpperCase()) {
            case "LENGTH":
                val1 = toInch(v1, u1);
                val2 = toInch(v2, u2);
                diff = fromInch(val1 - val2, u1);
                break;
            case "VOLUME":
                val1 = toLitre(v1, u1);
                val2 = toLitre(v2, u2);
                diff = fromLitre(val1 - val2, u1);
                break;
            case "WEIGHT":
                val1 = toKilogram(v1, u1);
                val2 = toKilogram(v2, u2);
                diff = fromKilogram(val1 - val2, u1);
                break;
            case "TEMPERATURE":
                throw new QuantityMeasurementException("Subtraction not supported for Temperature");
            default:
                throw new QuantityMeasurementException("Unknown Measurement Type: " + measurementType);
        }

        repo.save(new QuantityMeasurementEntity(
                v1, u1, v2, u2,
                OperationType.SUBTRACT,
                diff, u1
        ));

        if (userId != null && userId > 0) {
            userServiceClient.saveHistory(userId,
                new ConversionHistoryRequest(measurementType, u1, v1, u2, v2, diff));
        }

        return diff;
    }

    // ===== MULTIPLY =====
    public double multiply(double v1, String u1, double v2, String u2,
                           String measurementType, Long userId) {

        double val1, val2, result;

        switch (measurementType.toUpperCase()) {
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
                throw new QuantityMeasurementException("Unknown Measurement Type: " + measurementType);
        }

        result = val1 * val2;

        repo.save(new QuantityMeasurementEntity(
                v1, u1, v2, u2,
                OperationType.MULTIPLY,
                result, ""
        ));

        if (userId != null && userId > 0) {
            userServiceClient.saveHistory(userId,
                new ConversionHistoryRequest(measurementType, u1, v1, u2, v2, result));
        }

        return result;
    }

    // ===== DIVIDE =====
    public double divide(double v1, String u1, double v2, String u2,
                         String measurementType, Long userId) {

        double val1, val2, result;

        switch (measurementType.toUpperCase()) {
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
                throw new QuantityMeasurementException("Division not supported for Temperature");
            default:
                throw new QuantityMeasurementException("Unknown Measurement Type: " + measurementType);
        }

        if (val2 == 0) {
            throw new QuantityMeasurementException("Divide by zero");
        }

        result = val1 / val2;

        repo.save(new QuantityMeasurementEntity(
                v1, u1, v2, u2,
                OperationType.DIVIDE,
                result, ""
        ));

        if (userId != null && userId > 0) {
            userServiceClient.saveHistory(userId,
                new ConversionHistoryRequest(measurementType, u1, v1, u2, v2, result));
        }

        return result;
    }

    // ===== CONVERT =====
    public double convert(double value, String fromUnit, String toUnit,
                          String measurementType, Long userId) {

        double base, result;

        switch (measurementType.toUpperCase()) {
            case "LENGTH":
                base   = toInch(value, fromUnit);
                result = fromInch(base, toUnit);
                break;
            case "VOLUME":
                base   = toLitre(value, fromUnit);
                result = fromLitre(base, toUnit);
                break;
            case "WEIGHT":
                base   = toKilogram(value, fromUnit);
                result = fromKilogram(base, toUnit);
                break;
            case "TEMPERATURE":
                base   = toCelsius(value, fromUnit);
                result = fromCelsius(base, toUnit);
                break;
            default:
                throw new QuantityMeasurementException("Unknown Measurement Type: " + measurementType);
        }

        repo.save(new QuantityMeasurementEntity(
                value, fromUnit, 0, "",
                OperationType.CONVERT,
                result, toUnit
        ));

        if (userId != null && userId > 0) {
            userServiceClient.saveHistory(userId,
                new ConversionHistoryRequest(measurementType, fromUnit, value, toUnit, value, result));
        }

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