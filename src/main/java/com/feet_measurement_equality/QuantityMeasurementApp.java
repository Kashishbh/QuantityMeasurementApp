package com.feet_measurement_equality;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        // Equality 
        LengthQuantity q1=new LengthQuantity(1.0, LengthUnit.FEET);
        LengthQuantity q2=new LengthQuantity(12.0, LengthUnit.INCH);
        System.out.println(q1 + " equals " + q2 +" ? " + q1.equals(q2));
        //  Conversion (Static)
        double inches = LengthQuantity.convert(1.0, LengthUnit.FEET, LengthUnit.INCH);
        System.out.println("1 FEET in INCH = "+inches);
        // Conversion (Instance)
        LengthQuantity yard = new LengthQuantity(3.0, LengthUnit.YARD);
        LengthQuantity converted = yard.convertTo(LengthUnit.FEET);
        System.out.println(yard+" in FEET = "+converted);
        // CM to INCH
        double inchFromCm = LengthQuantity.convert(2.54,LengthUnit.CENTIMETERS, LengthUnit.INCH);
        System.out.println("2.54 CM in INCH = "+inchFromCm);
    }
}