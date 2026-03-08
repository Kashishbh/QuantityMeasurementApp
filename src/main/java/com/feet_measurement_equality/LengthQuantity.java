package com.feet_measurement_equality;

import java.util.Objects;

public class LengthQuantity {
    private static final double EPSILON = 1e-6;
    private final double value;
    private final LengthUnit unit;
    public LengthQuantity(double value, LengthUnit unit) {
        if (unit==null)
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
    // Convert current value to base unit (feet)
    public double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }
    // UC5 Conversion
    public LengthQuantity convertTo(LengthUnit targetUnit) {
        if (targetUnit==null)
            throw new IllegalArgumentException("Target unit cannot be null");
        double baseValue=this.toBaseUnit();
        double converted=targetUnit.convertFromBaseUnit(baseValue);
        return new LengthQuantity(converted, targetUnit);
    }
    // Static conversion 
    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (source==null || target==null)
            throw new IllegalArgumentException("Units cannot be null");
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");
        double baseValue=source.convertToBaseUnit(value);
        return target.convertFromBaseUnit(baseValue);
    }
    // UC6 Addition
    public LengthQuantity add(LengthQuantity other) {
        if (other==null)
            throw new IllegalArgumentException("Second operand cannot be null");
        double thisBase=this.toBaseUnit();
        double otherBase=other.toBaseUnit();
        double sum=thisBase+otherBase;
        double resultValue=this.unit.convertFromBaseUnit(sum);
        return new LengthQuantity(resultValue, this.unit);
    }
    // UC7 Addition with Target Unit
    public LengthQuantity add(LengthQuantity other, LengthUnit targetUnit) {
        if (other==null||targetUnit==null)
            throw new IllegalArgumentException("Operand or target unit cannot be null");
        double thisBase=this.toBaseUnit();
        double otherBase=other.toBaseUnit();
        double sum=thisBase+otherBase;
        double resultValue=targetUnit.convertFromBaseUnit(sum);
        return new LengthQuantity(resultValue,targetUnit);
    }
    // Equality check using epsilon
    @Override
    public boolean equals(Object obj) {
        if (this== obj) return true;
        if (!(obj instanceof LengthQuantity)) return false;
        LengthQuantity other=(LengthQuantity) obj;
        return Math.abs(this.toBaseUnit()-other.toBaseUnit())<EPSILON;
    }
    @Override
    public int hashCode() {
        return Objects.hash(Math.round(toBaseUnit()/EPSILON));
    }
    @Override
    public String toString() {
        return "Quantity("+value+", "+unit+")";
    }
}