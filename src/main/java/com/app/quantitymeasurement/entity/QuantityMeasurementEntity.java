package com.app.quantitymeasurement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quantity_measurement")
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double operand1;
    private String operand1Unit;
    private double operand2;
    private String operand2Unit;
    private String operation;
    private double result;
    private String resultUnit; 

    public QuantityMeasurementEntity() {}

    public QuantityMeasurementEntity(double operand1, String operand1Unit,double operand2, String operand2Unit,String operation, double result, String resultUnit) {
    	this.operand1 = operand1;
        this.operand1Unit = operand1Unit;
        this.operand2 = operand2;
        this.operand2Unit = operand2Unit;
        this.operation = operation;
        this.result = result;
        this.resultUnit = resultUnit;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getOperand1() {
		return operand1;
	}

	public void setOperand1(double operand1) {
		this.operand1 = operand1;
	}

	public String getOperand1Unit() {
		return operand1Unit;
	}

	public void setOperand1Unit(String operand1Unit) {
		this.operand1Unit = operand1Unit;
	}

	public double getOperand2() {
		return operand2;
	}

	public void setOperand2(double operand2) {
		this.operand2 = operand2;
	}

	public String getOperand2Unit() {
		return operand2Unit;
	}

	public void setOperand2Unit(String operand2Unit) {
		this.operand2Unit = operand2Unit;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
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