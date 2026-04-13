package com.app.user_service.dto;

public class ConversionHistoryRequest {
    private String measurementType;
    private String thisUnit;
    private double thisValue;
    private String thatUnit;
    private double thatValue;
    private double result;

    // Getters and setters for all fields
    public String getMeasurementType() { return measurementType; }
    public void setMeasurementType(String t) { this.measurementType = t; }
    public String getThisUnit() { return thisUnit; }
    public void setThisUnit(String u) { this.thisUnit = u; }
    public double getThisValue() { return thisValue; }
    public void setThisValue(double v) { this.thisValue = v; }
    public String getThatUnit() { return thatUnit; }
    public void setThatUnit(String u) { this.thatUnit = u; }
    public double getThatValue() { return thatValue; }
    public void setThatValue(double v) { this.thatValue = v; }
    public double getResult() { return result; }
    public void setResult(double r) { this.result = r; }
}
