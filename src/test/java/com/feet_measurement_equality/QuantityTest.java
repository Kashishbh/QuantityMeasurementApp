package com.feet_measurement_equality;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityTest {
	@Test
	void testEquality_FeetToInch_EquivalentValue() {
	    Quantity<LengthUnit>q1=new Quantity<>(1.0,LengthUnit.FEET);
	    Quantity<LengthUnit>q2=new Quantity<>(12.0,LengthUnit.INCHES);
	    assertTrue(q1.equals(q2));
	}
	@Test
	void testConversion_FeetToInch() {
	    Quantity<LengthUnit>q=new Quantity<>(1.0,LengthUnit.FEET);
	    Quantity<LengthUnit>converted=q.convertTo(LengthUnit.INCHES);
	    assertEquals(new Quantity<>(12.0, LengthUnit.INCHES),converted);
	}
	@Test
	void testAddition_KgAndGram() {
	    Quantity<WeightUnit>q1=new Quantity<>(1.0,WeightUnit.KILOGRAM);
	    Quantity<WeightUnit>q2=new Quantity<>(1000.0,WeightUnit.GRAM);
	    Quantity<WeightUnit>result=q1.add(q2);
	    assertEquals(new Quantity<>(2.0,WeightUnit.KILOGRAM),result);
	}
}
