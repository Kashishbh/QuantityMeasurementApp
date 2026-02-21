package com.feet_measurement_equality;
public class QuantityMeasurementApp {
    public static void main(String[] args) {
        LengthQuantity q1=new LengthQuantity(1.0,LengthUnit.FEET);
        LengthQuantity q2=new LengthQuantity(12.0,LengthUnit.INCH);
        System.out.println("Are Equal? "+q1.equals(q2));
    }
}