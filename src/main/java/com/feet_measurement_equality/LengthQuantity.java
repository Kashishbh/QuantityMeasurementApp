package com.feet_measurement_equality;
import java.util.Objects;

public class LengthQuantity {
    private final double value;
    private final LengthUnit unit;
    public LengthQuantity(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }
    public double toFeet() {
        return unit.toFeet(value);
    }
    @Override
    public boolean equals(Object obj) {
        if (this==obj)
            return true;
        if (obj==null)
            return false;
        if (getClass()!=obj.getClass())
            return false;

       LengthQuantity other=(LengthQuantity)obj;
        return Double.compare(this.toFeet(), other.toFeet()) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(toFeet());
    }
    @Override
    public String toString() {
        return "Quantity("+value+", "+unit+")";
    }
}