package com.feet_measurement_equality;

import java.util.Objects;
public class Quantity<U extends IMeasurable> {
    private static final double EPSILON=1e-6;
    private final double value;
    private final U unit;
    public Quantity(double value, U unit) {
        if (unit==null)
            throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");
        this.value=value;
        this.unit=unit;
    }
    public double getValue(){
        return value;
    }
    public U getUnit(){
        return unit;
    }
    public double toBaseUnit(){
        return unit.convertToBaseUnit(value);
    }
    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit==null)
            throw new IllegalArgumentException("Target unit cannot be null");
        double baseValue=this.toBaseUnit();
        double converted=targetUnit.convertFromBaseUnit(baseValue);
        return new Quantity<>(converted, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        double sum=this.toBaseUnit()+other.toBaseUnit();
        return new Quantity<>(unit.convertFromBaseUnit(sum),unit);
    }

    public Quantity<U>add(Quantity<U> other,U targetUnit) {
        double sum=this.toBaseUnit()+other.toBaseUnit();
        return new Quantity<>(targetUnit.convertFromBaseUnit(sum), targetUnit);
    }
    public Quantity<U> subtract(Quantity<U> other) {
        if (other==null)
            throw new IllegalArgumentException("Quantity cannot be null");
        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cannot subtract different measurement categories");
        double resultBase=this.toBaseUnit()-other.toBaseUnit();
        double converted=unit.convertFromBaseUnit(resultBase);
        return new Quantity<>(converted,unit);
    }
    public Quantity<U> subtract(Quantity<U> other,U targetUnit) {
        if (other==null)
            throw new IllegalArgumentException("Quantity cannot be null");
        if (targetUnit==null)
            throw new IllegalArgumentException("Target unit cannot be null");
        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cannot subtract different measurement categories");
        double resultBase=this.toBaseUnit()-other.toBaseUnit();
        double converted=targetUnit.convertFromBaseUnit(resultBase);
        return new Quantity<>(converted,targetUnit);
    }
    public double divide(Quantity<U> other) {
        if (other==null)
            throw new IllegalArgumentException("Quantity cannot be null");
        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cannot divide different measurement categories");
        double divisor=other.toBaseUnit();
        if (Math.abs(divisor)<EPSILON)
            throw new ArithmeticException("Division by zero");
        return this.toBaseUnit()/divisor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        if (obj==null||getClass()!=obj.getClass()) return false;

        Quantity<?>other=(Quantity<?>) obj;
        if (!unit.getClass().equals(other.unit.getClass())) return false;
        return Math.abs(this.toBaseUnit()-other.toBaseUnit())<EPSILON;
    }
    @Override
    public int hashCode() {
        return Objects.hash(Math.round(toBaseUnit()/EPSILON));
    }
    @Override
    public String toString() {
        return "Quantity("+value+", "+unit.getUnitName()+")";
    }
}