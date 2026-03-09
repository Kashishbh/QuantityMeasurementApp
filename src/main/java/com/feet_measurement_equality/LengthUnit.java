package com.feet_measurement_equality;
public enum LengthUnit implements IMeasurable {
    FEET(1.0),
    INCHES(0.0833333),
    YARDS(3.0),
    CENTIMETERS(0.0328084);
    private final double conversionFactor;
    LengthUnit(double factor) {
        this.conversionFactor=factor;
    }
    public double getConversionFactor() {
        return conversionFactor;
    }
    public double convertToBaseUnit(double value) {
        return value*conversionFactor;
    }
    public double convertFromBaseUnit(double baseValue) {
        return baseValue/conversionFactor;
    }
    public String getUnitName() {
        return this.name();
    }
}