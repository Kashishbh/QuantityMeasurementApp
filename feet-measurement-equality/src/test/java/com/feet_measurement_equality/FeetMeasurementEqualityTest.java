package com.feet_measurement_equality;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityLengthTest {
    @Test
    void testEquality_FeetToFeet_SameValue() {
        QuantityLength q1=new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2=new QuantityLength(1.0, LengthUnit.FEET);
        assertTrue(q1.equals(q2));
    }
    @Test
    void testEquality_InchToInch_SameValue() {
        QuantityLength q1=new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength q2=new QuantityLength(1.0, LengthUnit.INCH);
        assertTrue(q1.equals(q2));
    }
    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        QuantityLength q1=new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2=new QuantityLength(12.0, LengthUnit.INCH);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_DifferentValue() {
        QuantityLength q1=new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2=new QuantityLength(2.0, LengthUnit.FEET);
        assertFalse(q1.equals(q2));
    }
    @Test
    void testEquality_SameReference() {
        QuantityLength q1=new QuantityLength(1.0, LengthUnit.FEET);
        assertTrue(q1.equals(q1));
    }
    @Test
    void testEquality_NullComparison() {
        QuantityLength q1=new QuantityLength(1.0, LengthUnit.FEET);
        assertFalse(q1.equals(null));
    }
    @Test
    void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityLength(1.0, null);
        });
    }
}
