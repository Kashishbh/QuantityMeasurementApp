package com.app.quantitymeasurement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "quantity_measurement_entity")
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double thisValue;
    private String thisUnit;

    private double thatValue;
    private String thatUnit;

    @Enumerated(EnumType.STRING)
    private OperationType operation;

    private double result;
    private String resultUnit;

    // ===== Default Constructor =====
    public QuantityMeasurementEntity() {}

    // ===== Parameterized Constructor =====
    public QuantityMeasurementEntity(double thisValue, String thisUnit,
                                     double thatValue, String thatUnit,
                                     OperationType operation,
                                     double result, String resultUnit) {
        this.thisValue = thisValue;
        this.thisUnit = thisUnit;
        this.thatValue = thatValue;
        this.thatUnit = thatUnit;
        this.operation = operation;
        this.result = result;
        this.resultUnit = resultUnit;
    }

    // ===== Getters & Setters =====

    public long getId() {
        return id;
    }

    public double getThisValue() {
        return thisValue;
    }

    public void setThisValue(double thisValue) {
        this.thisValue = thisValue;
    }

    public String getThisUnit() {
        return thisUnit;
    }

    public void setThisUnit(String thisUnit) {
        this.thisUnit = thisUnit;
    }

    public double getThatValue() {
        return thatValue;
    }

    public void setThatValue(double thatValue) {
        this.thatValue = thatValue;
    }

    public String getThatUnit() {
        return thatUnit;
    }

    public void setThatUnit(String thatUnit) {
        this.thatUnit = thatUnit;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getResultUnit() {
        return resultUnit;
    }

    public void setResultUnit(String resultUnit) {
        this.resultUnit = resultUnit;
    }
}