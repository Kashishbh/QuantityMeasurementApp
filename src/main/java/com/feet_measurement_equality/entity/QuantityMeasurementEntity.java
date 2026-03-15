package com.feet_measurement_equality.entity;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {

    private double operand1;
    private double operand2;
    private String operation;
    private double result;

    public QuantityMeasurementEntity(double operand1, double operand2, String operation, double result) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = operation;
        this.result = result;
    }

    public double getOperand1() {
        return operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public String getOperation() {
        return operation;
    }

    public double getResult() {
        return result;
    }

    // 🔹 OPTIONAL but good practice (not mandatory)
    @Override
    public String toString() {
        return "QuantityMeasurementEntity{" +
                "operand1=" + operand1 +
                ", operand2=" + operand2 +
                ", operation='" + operation + '\'' +
                ", result=" + result +
                '}';
    }
}