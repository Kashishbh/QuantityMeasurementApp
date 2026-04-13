package com.app.user_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "conversion_history")
public class ConversionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String measurementType;
    private String thisUnit;
    private double thisValue;
    private String thatUnit;
    private double thatValue;
    private double result;
    private LocalDateTime timestamp;

    // Generate getters and setters in IntelliJ:
    // Right-click inside class → Generate → Getters and Setters

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
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
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}