package com.app.quantitymeasurement.dto;

public class QuantityDTO {

    private double value;
    private String unit;

    // Default constructor
    public QuantityDTO() {}

    // Getters & Setters
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}