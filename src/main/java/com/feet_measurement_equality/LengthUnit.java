package com.feet_measurement_equality;
public enum LengthUnit {
    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CENTIMETERS(0.0328084);
    private final double toFeetFactor;
    LengthUnit(double toFeetFactor){
        this.toFeetFactor=toFeetFactor;
    }
    public double toFeet(double value){
        return value*toFeetFactor;
    }
    public double fromFeet(double feetValue){
        return feetValue/toFeetFactor;
    }
    public double getFactor(){
        return toFeetFactor;
    }
}