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
    private enum ArithmeticOperation {
        ADD {
            public double compute(double a,double b){
                return a + b;
            }
        },
        SUBTRACT{
            public double compute(double a,double b) {
                return a-b;
            }
        },
        DIVIDE {
            public double compute(double a, double b) {
                if (Math.abs(b) < EPSILON)
                    throw new ArithmeticException("Division by zero");
                return a/b;
            }
        };
        public abstract double compute(double a,double b);
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
    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired) {
        if (other==null)
            throw new IllegalArgumentException("Quantity cannot be null");
        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different measurement categories");
        if (targetUnitRequired&&targetUnit==null)
            throw new IllegalArgumentException("Target unit cannot be null");
        if (!Double.isFinite(this.value)||!Double.isFinite(other.value))
            throw new IllegalArgumentException("Invalid value");
    }
    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {
        double baseThis=this.toBaseUnit();
        double baseOther=other.toBaseUnit();
        return operation.compute(baseThis,baseOther);
    }
    public Quantity<U> add(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        double resultBase=performBaseArithmetic(other, ArithmeticOperation.ADD);
        return new Quantity<>(unit.convertFromBaseUnit(resultBase),unit);
    }
    public Quantity<U> add(Quantity<U> other,U targetUnit) {
        validateArithmeticOperands(other,targetUnit,true);
        double resultBase=performBaseArithmetic(other,ArithmeticOperation.ADD);
        return new Quantity<>(targetUnit.convertFromBaseUnit(resultBase), targetUnit);
    }
    public Quantity<U> subtract(Quantity<U> other) {
        validateArithmeticOperands(other,null,false);
        double resultBase=performBaseArithmetic(other,ArithmeticOperation.SUBTRACT);
        return new Quantity<>(unit.convertFromBaseUnit(resultBase), unit);
    }
    public Quantity<U> subtract(Quantity<U> other,U targetUnit) {
        validateArithmeticOperands(other,targetUnit,true);
        double resultBase=performBaseArithmetic(other,ArithmeticOperation.SUBTRACT);
        return new Quantity<>(targetUnit.convertFromBaseUnit(resultBase),targetUnit);
    }
    public double divide(Quantity<U> other) {
        validateArithmeticOperands(other,null,false);
        return performBaseArithmetic(other,ArithmeticOperation.DIVIDE);
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