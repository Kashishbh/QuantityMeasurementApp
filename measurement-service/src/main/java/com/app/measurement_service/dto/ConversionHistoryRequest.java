package com.app.measurement_service.dto;

public class ConversionHistoryRequest {

    private String measurementType;
    private String thisUnit;
    private double thisValue;
    private String thatUnit;
    private double thatValue;
    private double result;

    public ConversionHistoryRequest() {}

    public ConversionHistoryRequest(String measurementType,
                                    String thisUnit, double thisValue,
                                    String thatUnit, double thatValue,
                                    double result) {
        this.measurementType = measurementType;
        this.thisUnit = thisUnit;
        this.thisValue = thisValue;
        this.thatUnit = thatUnit;
        this.thatValue = thatValue;
        this.result = result;
    }

    public String getMeasurementType() { return measurementType; }
    public void setMeasurementType(String measurementType) { this.measurementType = measurementType; }

    public String getThisUnit() { return thisUnit; }
    public void setThisUnit(String thisUnit) { this.thisUnit = thisUnit; }

    public double getThisValue() { return thisValue; }
    public void setThisValue(double thisValue) { this.thisValue = thisValue; }

    public String getThatUnit() { return thatUnit; }
    public void setThatUnit(String thatUnit) { this.thatUnit = thatUnit; }

    public double getThatValue() { return thatValue; }
    public void setThatValue(double thatValue) { this.thatValue = thatValue; }

    public double getResult() { return result; }
    public void setResult(double result) { this.result = result; }
}