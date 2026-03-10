package com.feet_measurement_equality;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable> void demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {
        System.out.println(q1+" equals "+q2+" -> "+ q1.equals(q2));
    }
    public static <U extends IMeasurable> void demonstrateConversion(Quantity<U> q, U targetUnit) {
        System.out.println(q +" converted to "+targetUnit.getUnitName() + " -> " + q.convertTo(targetUnit));
    }
    public static <U extends IMeasurable> void demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        System.out.println(q1 +" + "+q2+" in "+targetUnit.getUnitName() + " -> " + q1.add(q2, targetUnit));
    }
    public static void main(String[] args) {
        Quantity<LengthUnit> l1=new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2=new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<WeightUnit> w1=new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2=new Quantity<>(1000.0, WeightUnit.GRAM);
        demonstrateEquality(l1,l2);
        demonstrateConversion(l1,LengthUnit.INCHES);
        demonstrateAddition(l1,l2,LengthUnit.FEET);
        demonstrateEquality(w1,w2);
        demonstrateConversion(w1,WeightUnit.GRAM);
        demonstrateAddition(w1,w2,WeightUnit.KILOGRAM);
        
        Quantity<VolumeUnit>volume1=new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit>volume2=new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit>volume3=new Quantity<>(1.0, VolumeUnit.GALLON);
        // Equality
        System.out.println("1 L == 1000 mL: "+volume1.equals(volume2));
        // Conversion
        Quantity<VolumeUnit> converted=volume1.convertTo(VolumeUnit.MILLILITRE);
        System.out.println("1 L in mL: "+converted);
        // Addition (implicit unit)
        Quantity<VolumeUnit>sum1=volume1.add(volume2);
        System.out.println("1 L + 1000 mL: "+sum1);
        // Addition (explicit unit)
        Quantity<VolumeUnit>sum2=volume1.add(volume3,VolumeUnit.MILLILITRE);
        System.out.println("1 L + 1 Gallon in mL: "+sum2);
    }
}