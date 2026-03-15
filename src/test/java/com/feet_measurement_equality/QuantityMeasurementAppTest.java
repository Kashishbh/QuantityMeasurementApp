package com.feet_measurement_equality;

import com.feet_measurement_equality.controller.QuantityMeasurementController;
import com.feet_measurement_equality.dto.QuantityDTO;
import com.feet_measurement_equality.repository.QuantityMeasurementCacheRepository;
import com.feet_measurement_equality.service.IQuantityMeasurementService;
import com.feet_measurement_equality.service.QuantityMeasurementServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private IQuantityMeasurementService service;
    private QuantityMeasurementController controller;

    @BeforeEach
    void setUp() {

        service = new QuantityMeasurementServiceImpl(
                QuantityMeasurementCacheRepository.getInstance());

        controller = new QuantityMeasurementController(service);
    }

    // DTO Test
    @Test
    void testQuantityDTOCreation() {

        QuantityDTO dto = new QuantityDTO(10, "FEET", "LENGTH");

        assertEquals(10.0, dto.getValue());
        assertEquals("FEET", dto.getUnit());
        assertEquals("LENGTH", dto.getMeasurementType());
    }

    // Service Layer Tests

    @Test
    void testServiceCompareEqualitySameUnit() {

        QuantityDTO q1 = new QuantityDTO(10, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(10, "FEET", "LENGTH");

        assertTrue(service.compare(q1, q2));
    }

    @Test
    void testServiceAddition() {

        QuantityDTO q1 = new QuantityDTO(5, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(5, "FEET", "LENGTH");

        QuantityDTO result = service.add(q1, q2);

        assertEquals(10.0, result.getValue());
        assertEquals("FEET", result.getUnit());
    }

    @Test
    void testServiceSubtraction() {

        QuantityDTO q1 = new QuantityDTO(10, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(5, "FEET", "LENGTH");

        QuantityDTO result = service.subtract(q1, q2);

        assertEquals(5.0, result.getValue());
    }

    @Test
    void testServiceDivision() {

        QuantityDTO q1 = new QuantityDTO(10, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(2, "FEET", "LENGTH");

        double result = service.divide(q1, q2);

        assertEquals(5.0, result);
    }

    @Test
    void testServiceDivisionByZero() {

        QuantityDTO q1 = new QuantityDTO(10, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(0, "FEET", "LENGTH");

        assertThrows(RuntimeException.class,
                () -> service.divide(q1, q2));
    }

    // Controller Layer Tests

    @Test
    void testControllerCompareOperation() {

        QuantityDTO q1 = new QuantityDTO(10, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(10, "FEET", "LENGTH");

        assertDoesNotThrow(() ->
                controller.performComparison(q1, q2));
    }

    @Test
    void testControllerAdditionOperation() {

        QuantityDTO q1 = new QuantityDTO(5, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(5, "FEET", "LENGTH");

        assertDoesNotThrow(() ->
                controller.performAddition(q1, q2));
    }

    @Test
    void testControllerConversionOperation() {

        QuantityDTO q1 = new QuantityDTO(1, "FEET", "LENGTH");

        assertDoesNotThrow(() ->
                controller.performConversion(q1, "INCH"));
    }

    // Integration Test

    @Test
    void testIntegrationEndToEndAddition() {

        QuantityDTO q1 = new QuantityDTO(10, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(20, "FEET", "LENGTH");

        QuantityDTO result = service.add(q1, q2);

        assertEquals(30.0, result.getValue());
    }
}