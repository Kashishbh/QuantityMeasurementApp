package com.feet_measurement_equality.model;

import com.feet_measurement_equality.unit.IMeasurable;
public class QuantityModel<U extends IMeasurable> {
    private double value;
    private U unit;

    public QuantityModel(double value,U unit) {
        this.value=value;
        this.unit=unit;
    }

    public double getValue() {
        return value;
    }
    public U getUnit() {
        return unit;
    }
    public double getBaseValue() {
        return unit.convertToBaseUnit(value);
    }
}