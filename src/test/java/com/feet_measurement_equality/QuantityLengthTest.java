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
    private static final double EPSILON = 1e-6;
    @Test
    void testConversion_FeetToInch() {
        double result = LengthQuantity.convert(1.0, LengthUnit.FEET, LengthUnit.INCH);
        assertEquals(12.0, result, EPSILON);
    }
    @Test
    void testConversion_InchToFeet() {
        double result = LengthQuantity.convert(24.0, LengthUnit.INCH, LengthUnit.FEET);
        assertEquals(2.0, result, EPSILON);
    }
    @Test
    void testConversion_YardToFeet() {
        double result = LengthQuantity.convert(1.0, LengthUnit.YARD, LengthUnit.FEET);
        assertEquals(3.0, result, EPSILON);
    }
    @Test
    void testConversion_CmToInch() {
        double result = LengthQuantity.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCH);
        assertEquals(1.0, result, 1e-3);
    }
    @Test
    void testConversion_SameUnit() {
        double result = LengthQuantity.convert(5.0, LengthUnit.FEET, LengthUnit.FEET);
        assertEquals(5.0, result, EPSILON);
    }
    @Test
    void testConversion_ZeroValue() {
        double result = LengthQuantity.convert(0.0, LengthUnit.FEET, LengthUnit.INCH);
        assertEquals(0.0, result, EPSILON);
    }
    @Test
    void testConversion_NegativeValue() {
        double result = LengthQuantity.convert(-1.0, LengthUnit.FEET, LengthUnit.INCH);
        assertEquals(-12.0, result, EPSILON);
    }
    @Test
    void testConversion_RoundTrip() {
        double original = 5.0;
        double converted = LengthQuantity.convert(original, LengthUnit.FEET, LengthUnit.INCH);
        double back = LengthQuantity.convert(converted, LengthUnit.INCH, LengthUnit.FEET);
        assertEquals(original, back, EPSILON);
    }
    @Test
    void testConversion_NullUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            LengthQuantity.convert(1.0, null, LengthUnit.FEET);
        });
    }
    @Test
    void testConversion_NaN_Throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            LengthQuantity.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCH);
        });
    }
    @Test
    void testAddFeetAndInch() {
        LengthQuantity q1=new LengthQuantity(1.0, LengthUnit.FEET);
        LengthQuantity q2=new LengthQuantity(12.0, LengthUnit.INCH);
        LengthQuantity result=q1.add(q2);
        assertEquals(new LengthQuantity(2.0, LengthUnit.FEET), result);
    }
    @Test
    void testFeetEquality() {
        LengthQuantity q1=new LengthQuantity(1.0, LengthUnit.FEET);
        LengthQuantity q2=new LengthQuantity(1.0, LengthUnit.FEET);
        assertEquals(q1,q2);
    }
    @Test
    void testAddYardAndFeet() {
        LengthQuantity q1=new LengthQuantity(1.0, LengthUnit.YARD);
        LengthQuantity q2=new LengthQuantity(3.0, LengthUnit.FEET);
        LengthQuantity result=q1.add(q2);
        assertEquals(new LengthQuantity(2.0, LengthUnit.YARD), result);
    }
    @Test
    void testAdditionTargetFeet() {
        LengthQuantity q1=new LengthQuantity(1.0,LengthUnit.FEET);
        LengthQuantity q2=new LengthQuantity(12.0,LengthUnit.INCH);
        LengthQuantity result=q1.add(q2,LengthUnit.FEET);
        assertEquals(new LengthQuantity(2.0,LengthUnit.FEET),result);
    }
    @Test
    void testUnitConversion_ToBaseUnit() {
        double result = LengthUnit.INCH.convertToBaseUnit(12.0);
        assertEquals(1.0, result, EPSILON);
    }

    @Test
    void testUnitConversion_FromBaseUnit() {
        double result = LengthUnit.INCH.convertFromBaseUnit(1.0);
        assertEquals(12.0, result, EPSILON);
    }
}
