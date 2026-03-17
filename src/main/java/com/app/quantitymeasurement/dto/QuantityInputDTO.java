package com.app.quantitymeasurement.dto;

public class QuantityInputDTO {

    private String measurementType; // LENGTH, VOLUME, WEIGHT, TEMPERATURE
    private QuantityDTO thisQuantityDTO;
    private QuantityDTO thatQuantityDTO;

    // Getters and Setters
    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public QuantityDTO getThisQuantityDTO() {
        return thisQuantityDTO;
    }

    public void setThisQuantityDTO(QuantityDTO thisQuantityDTO) {
        this.thisQuantityDTO = thisQuantityDTO;
    }

    public QuantityDTO getThatQuantityDTO() {
        return thatQuantityDTO;
    }

    public void setThatQuantityDTO(QuantityDTO thatQuantityDTO) {
        this.thatQuantityDTO = thatQuantityDTO;
    }
}