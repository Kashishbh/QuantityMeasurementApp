package com.feet_measurement_equality;

public interface IMeasurable {
    double getConversionFactor();                
    double convertToBaseUnit(double value);      
    double convertFromBaseUnit(double baseValue); 
    String getUnitName();   
    default boolean supportsArithmetic() {
        return true;
    }
    default void validateOperationSupport(String operation) {
    }
}