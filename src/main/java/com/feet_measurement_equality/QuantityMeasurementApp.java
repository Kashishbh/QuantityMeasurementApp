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
        //Add
        //Inch+Feet
        LengthQuantity q3=new LengthQuantity(12.0,LengthUnit.INCH);
        LengthQuantity q4=new LengthQuantity(1.0,LengthUnit.FEET);
        LengthQuantity result1=q3.add(q4);
        System.out.println(q3+" + "+q4+" = "+ result1);
        // Feet+Feet
        LengthQuantity q5=new LengthQuantity(2.0, LengthUnit.FEET);
        LengthQuantity q6=new LengthQuantity(3.0, LengthUnit.FEET);
        LengthQuantity result2=q5.add(q6);
        System.out.println(q5 +" + "+ q6+" = "+result2);
        // Yard+Feet
        LengthQuantity q7=new LengthQuantity(1.0, LengthUnit.YARD);
        LengthQuantity q8=new LengthQuantity(3.0, LengthUnit.FEET);
        LengthQuantity result3=q7.add(q8);
        System.out.println(q7+ " + "+q8+" = "+result3);
    }
}