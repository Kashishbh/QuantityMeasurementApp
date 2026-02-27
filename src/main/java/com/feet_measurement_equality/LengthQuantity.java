package com.feet_measurement_equality;

import java.util.Objects;
public class LengthQuantity {
    private static final double EPSILON = 1e-6;
    private final double value;
    private final LengthUnit unit;
    public LengthQuantity(double value,LengthUnit unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");
        this.value=value;
        this.unit=unit;
    }
    public double getValue() {
        return value;
    }
    public LengthUnit getUnit() {
        return unit;
    }
    // Convert current object to feet (base unit)
    public double toFeet() {
        return unit.toFeet(value);
    }
    //  Instance method conversion (UC5)
    public LengthQuantity convertTo(LengthUnit targetUnit) {
        if (targetUnit==null)
            throw new IllegalArgumentException("Target unit cannot be null");
        double valueInFeet=this.toFeet();
        double convertedValue=targetUnit.fromFeet(valueInFeet);
        return new LengthQuantity(convertedValue, targetUnit);
    }
    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (source==null||target==null)
            throw new IllegalArgumentException("Units cannot be null");
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");
        double valueInFeet=source.toFeet(value);
        return target.fromFeet(valueInFeet);
    }
    // Improved equality using epsilon
    @Override
    public boolean equals(Object obj) {
        if (this==obj)return true;
        if (!(obj instanceof LengthQuantity)) return false;
        LengthQuantity other=(LengthQuantity)obj;
        return Math.abs(this.toFeet()-other.toFeet())<EPSILON;
    }
    @Override
    public int hashCode() {
        return Objects.hash(Math.round(toFeet()/EPSILON));
    }
    @Override
    public String toString() {
        return "Quantity("+value+", "+unit+")";
    }
}