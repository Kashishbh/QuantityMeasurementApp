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
	@Test
	void testEquality_LitreToMillilitre() {
	    Quantity<VolumeUnit> v1=new Quantity<>(1.0,VolumeUnit.LITRE);
	    Quantity<VolumeUnit> v2=new Quantity<>(1000.0,VolumeUnit.MILLILITRE);

	    assertTrue(v1.equals(v2));
	}

	@Test
	void testConversion_LitreToMillilitre() {
	    Quantity<VolumeUnit> v1=new Quantity<>(1.0,VolumeUnit.LITRE);
	    Quantity<VolumeUnit> result=v1.convertTo(VolumeUnit.MILLILITRE);
	    assertEquals(1000.0, result.getValue(), 0.001);
	}

	@Test
	void testAddition_LitrePlusMillilitre() {
	    Quantity<VolumeUnit> v1=new Quantity<>(1.0,VolumeUnit.LITRE);
	    Quantity<VolumeUnit> v2=new Quantity<>(1000.0,VolumeUnit.MILLILITRE);
	    Quantity<VolumeUnit> result = v1.add(v2);
	    assertEquals(2.0, result.getValue(), 0.001);
	}
}
