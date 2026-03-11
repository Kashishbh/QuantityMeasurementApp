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
	@Test
	void testSubtraction_SameUnit() {
	    Quantity<VolumeUnit>q1=new Quantity<>(5.0,VolumeUnit.LITRE);
	    Quantity<VolumeUnit>q2=new Quantity<>(2.0,VolumeUnit.LITRE);
	    Quantity<VolumeUnit>result=q1.subtract(q2);
	    assertEquals(3.0, result.getValue(),0.001);
	}
	@Test
	void testDivision_SameUnit() {
	    Quantity<VolumeUnit>q1=new Quantity<>(10.0, VolumeUnit.LITRE);
	    Quantity<VolumeUnit>q2=new Quantity<>(5.0, VolumeUnit.LITRE);
	    double result=q1.divide(q2);
	    assertEquals(2.0,result,0.001);
	}
	@Test
	public void shouldConvertCelsiusToFahrenheit() {
	    Quantity<TemperatureUnit> temp=new Quantity<>(0,TemperatureUnit.CELSIUS);
	    Quantity<TemperatureUnit> result=temp.convertTo(TemperatureUnit.FAHRENHEIT);
	    assertEquals(new Quantity<>(32,TemperatureUnit.FAHRENHEIT),result);
	}
	@Test
	public void shouldConvertFahrenheitToCelsius() {
	    Quantity<TemperatureUnit> temp=new Quantity<>(32, TemperatureUnit.FAHRENHEIT);
	    Quantity<TemperatureUnit> result=temp.convertTo(TemperatureUnit.CELSIUS);
	    assertEquals(new Quantity<>(0, TemperatureUnit.CELSIUS),result);
	}
	@Test
	public void shouldThrowExceptionWhenAddingTemperature() {
	    Quantity<TemperatureUnit> t1=new Quantity<>(10, TemperatureUnit.CELSIUS);
	    Quantity<TemperatureUnit> t2=new Quantity<>(20, TemperatureUnit.CELSIUS);
	    assertThrows(UnsupportedOperationException.class,()->{
	        t1.add(t2);
	    });
	}
	
}
