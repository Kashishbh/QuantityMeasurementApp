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
 // UC6: Add two length quantities
    public LengthQuantity add(LengthQuantity other) {

        if (other == null) {
            throw new IllegalArgumentException("Second operand cannot be null");
        }

        // Step 1: Convert both quantities to base unit (feet)
        double thisFeet = this.toFeet();
        double otherFeet = other.toFeet();

        // Step 2: Add them
        double sumFeet = thisFeet + otherFeet;

        // Step 3: Convert result back to unit of first operand
        double resultValue = this.unit.fromFeet(sumFeet);

        // Step 4: Return new Quantity object
        return new LengthQuantity(resultValue, this.unit);
    }
    //UC7 Target Unit Addition
    public LengthQuantity add(LengthQuantity other, LengthUnit targetUnit) {
        if (other==null||targetUnit==null) {
            throw new IllegalArgumentException("target unit cannot be null");
        }
        double thisFeet=this.unit.toFeet(this.value);
        double otherFeet=other.unit.toFeet(other.value);
        double sumFeet=thisFeet + otherFeet;
        double resultValue=targetUnit.fromFeet(sumFeet);
        return new LengthQuantity(resultValue,targetUnit);
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