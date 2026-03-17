package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QuantityMeasurementServiceTest {

    @Autowired
    private QuantityMeasurementService service;

    @Autowired
    private QuantityMeasurementRepository repo;

    // ===== LENGTH TEST =====
    @Test
    void givenFeetAndInch_WhenCompared_ShouldReturnTrue() {
        boolean result = service.compare(1, "FEET", 12, "INCH", "LENGTH");
        assertTrue(result);
    }

    // ===== ADD LENGTH =====
    @Test
    void givenLength_WhenAdded_ShouldReturnCorrectResult() {
        double result = service.add(1, "FEET", 6, "INCH", "LENGTH");
        assertEquals(1.5, result);
    }

    // ===== ADD VOLUME =====
    @Test
    void givenVolume_WhenAdded_ShouldReturnCorrectResult() {
        double result = service.add(1, "LITRE", 500, "MILLILITRE", "VOLUME");
        assertEquals(1.5, result);
    }

    // ===== SUBTRACT WEIGHT =====
    @Test
    void givenWeight_WhenSubtracted_ShouldReturnCorrectResult() {
        double result = service.subtract(5, "KILOGRAM", 500, "GRAM", "WEIGHT");
        assertEquals(4.5, result);
    }

    // ===== DIVIDE LENGTH =====
    @Test
    void givenLength_WhenDivided_ShouldReturnCorrectResult() {
        double result = service.divide(2, "FEET", 6, "INCH", "LENGTH");
        assertEquals(4, result);
    }

    // ===== CONVERT LENGTH =====
    @Test
    void givenFeet_WhenConvertedToInch_ShouldReturn12() {
        double result = service.convert(1, "FEET", "INCH", "LENGTH");
        assertEquals(12, result);
    }

    // ===== CONVERT VOLUME =====
    @Test
    void givenLitre_WhenConvertedToML_ShouldReturn1000() {
        double result = service.convert(1, "LITRE", "MILLILITRE", "VOLUME");
        assertEquals(1000, result);
    }

    // ===== CONVERT TEMPERATURE =====
    @Test
    void givenCelsius_WhenConvertedToFahrenheit_ShouldReturn212() {
        double result = service.convert(100, "CELSIUS", "FAHRENHEIT", "TEMPERATURE");
        assertEquals(212, result);
    }

    // ===== INVALID TEMPERATURE ADD =====
    @Test
    void givenTemperature_WhenAdd_ShouldThrowException() {
        assertThrows(RuntimeException.class, () -> {
            service.add(10, "CELSIUS", 20, "CELSIUS", "TEMPERATURE");
        });
    }

    // ===== DIVIDE BY ZERO =====
    @Test
    void givenZero_WhenDivide_ShouldThrowException() {
        assertThrows(ArithmeticException.class, () -> {
            service.divide(10, "FEET", 0, "INCH", "LENGTH");
        });
    }
}