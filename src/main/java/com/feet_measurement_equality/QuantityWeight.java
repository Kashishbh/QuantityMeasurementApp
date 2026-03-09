package com.feet_measurement_equality;

import java.util.Objects;
public class QuantityWeight {
    private static final double EPSILON=1e-6;
    private final double value;
    private final WeightUnit unit;
    public QuantityWeight(double value, WeightUnit unit){
        if(unit==null)
            throw new IllegalArgumentException("Unit cannot be null");
        if(!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");
        this.value=value;
        this.unit=unit;
    }
    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }
    public double toBaseUnit(){
        return unit.convertToBaseUnit(value);
    }
    public QuantityWeight convertTo(WeightUnit targetUnit){
        if(targetUnit==null)
            throw new IllegalArgumentException("Target unit cannot be null");
        double baseValue=this.toBaseUnit();
        double converted=targetUnit.convertFromBaseUnit(baseValue);
        return new QuantityWeight(converted,targetUnit);
    }
    public QuantityWeight add(QuantityWeight other){
        double sum=this.toBaseUnit() + other.toBaseUnit();
        double result=unit.convertFromBaseUnit(sum);
        return new QuantityWeight(result,unit);
    }
    public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit){
        double sum=this.toBaseUnit() + other.toBaseUnit();
        double result=targetUnit.convertFromBaseUnit(sum);
        return new QuantityWeight(result,targetUnit);
    }
    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(obj==null||getClass()!=obj.getClass())
            return false;
        QuantityWeight other=(QuantityWeight)obj;
        return Math.abs(this.toBaseUnit()-other.toBaseUnit())<EPSILON;
    }
    @Override
    public int hashCode(){
        return Objects.hash(Math.round(toBaseUnit()/EPSILON));
    }
    @Override
    public String toString(){
        return "Quantity("+value+", "+unit+")";
    }
}