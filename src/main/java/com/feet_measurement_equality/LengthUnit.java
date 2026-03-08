package com.feet_measurement_equality;
public enum LengthUnit {
    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CENTIMETERS(0.0328084);
	private final double conversionFactor;
    LengthUnit(double conversionFactor) {
        this.conversionFactor=conversionFactor;
    }
    public double convertToBaseUnit(double value) {
        return value*conversionFactor;
    }
    public double convertFromBaseUnit(double baseValue) {
        return baseValue/conversionFactor;
    }
    public double getConversionFactor() {
        return conversionFactor;
    }
}