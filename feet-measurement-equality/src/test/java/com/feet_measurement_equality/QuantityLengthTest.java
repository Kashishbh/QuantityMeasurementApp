package com.feet_measurement_equality;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityLengthTest {
    @Test
    void testEquality_FeetToFeet_SameValue() {
        LengthQuantity q1=new LengthQuantity(1.0,LengthUnit.FEET);
        LengthQuantity q2=new LengthQuantity(1.0,LengthUnit.FEET);
        assertTrue(q1.equals(q2));
    }
    @Test
    void testEquality_InchToInch_SameValue() {
        LengthQuantity q1=new LengthQuantity(1.0,LengthUnit.INCH);
        LengthQuantity q2=new LengthQuantity(1.0,LengthUnit.INCH);
        assertTrue(q1.equals(q2));
    }
    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        LengthQuantity q1=new LengthQuantity(1.0,LengthUnit.FEET);
        LengthQuantity q2=new LengthQuantity(12.0,LengthUnit.INCH);
        assertTrue(q1.equals(q2));
    }
    @Test
    void testEquality_DifferentValue() {
        LengthQuantity q1=new LengthQuantity(1.0,LengthUnit.FEET);
        LengthQuantity q2=new LengthQuantity(2.0,LengthUnit.FEET);
        assertFalse(q1.equals(q2));
    }
    @Test
    void testEquality_SameReference() {
        LengthQuantity q1=new LengthQuantity(1.0,LengthUnit.FEET);
        assertTrue(q1.equals(q1));
    }
    @Test
    void testEquality_NullComparison() {
        LengthQuantity q1=new LengthQuantity(1.0,LengthUnit.FEET);
        assertFalse(q1.equals(null));
    }
    @Test
    void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class,()->{
            new LengthQuantity(1.0, null);
        });
    }
}