package com.feet_measurement_equality;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class FeetMeasurementEqualityTest {
	@Test
    void testEquality_SameValue() {
		FeetMeasurementEquality.Feet first = new FeetMeasurementEquality.Feet(1.0);
		FeetMeasurementEquality.Feet second = new FeetMeasurementEquality.Feet(1.0);

        assertTrue(first.equals(second),"1.0 ft should be equal to 1.0 ft");
    }
    @Test
    void testEquality_DifferentValue() {
    	FeetMeasurementEquality.Feet first = new FeetMeasurementEquality.Feet(1.0);
    	FeetMeasurementEquality.Feet second = new FeetMeasurementEquality.Feet(2.0);

        assertFalse(first.equals(second),"1.0 ft should not be equal to 2.0 ft");
    }
    @Test
    void testEquality_NullComparison() {
    	FeetMeasurementEquality.Feet first=new FeetMeasurementEquality.Feet(1.0);

        assertFalse(first.equals(null),"Value should not be equal to null");
    }
    @Test
    void testEquality_SameReference() {
    	FeetMeasurementEquality.Feet first=new FeetMeasurementEquality.Feet(1.0);

        assertTrue(first.equals(first),"Object must be equal to itself");
    }
    @Test
    void testEquality_DifferentType() {
    	FeetMeasurementEquality.Feet first = new FeetMeasurementEquality.Feet(1.0);

        assertFalse(first.equals("1.0"),"Feet should not be equal to a String");
    }

}
