package com.feet_measurement_equality.dto;

public class QuantityDTO {

    private double value;
    private String unit;
    private String measurementType;

    // Constructor used in Service
    public QuantityDTO(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    // Constructor used in Test
    public QuantityDTO(double value, String unit, String measurementType) {
        this.value = value;
        this.unit = unit;
        this.measurementType = measurementType;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }
}