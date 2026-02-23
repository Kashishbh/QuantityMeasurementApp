package com.feet_measurement_equality;
public class QuantityMeasurementApp {
    public static void main(String[] args) {
        LengthQuantity yard=new LengthQuantity(1.0,LengthUnit.FEET);
        LengthQuantity feet=new LengthQuantity(12.0,LengthUnit.INCH);
        LengthQuantity inches = new LengthQuantity(36.0, LengthUnit.INCH);
        LengthQuantity cm = new LengthQuantity(1.0, LengthUnit.CENTIMETERS);
        LengthQuantity inchFromCm = new LengthQuantity(0.393701, LengthUnit.INCH);
        System.out.println(yard+" equals "+feet+" ? "+yard.equals(feet));
        System.out.println(yard+" equals "+inches+" ? "+yard.equals(inches));
        System.out.println(cm+" equals "+inchFromCm+" ? "+cm.equals(inchFromCm));
    }
}
