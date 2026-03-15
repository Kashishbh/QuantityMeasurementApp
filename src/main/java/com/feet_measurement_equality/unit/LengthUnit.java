package com.feet_measurement_equality.unit;

public enum LengthUnit implements IMeasurable {
	INCH(0.0254),
    FEET(0.3048),
    YARD(0.9144),
    CM(0.01),
    METER(1),
    KM(1000);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    // convert given unit to base unit (meter)
    public double toBaseUnit(double value) {
        return value * conversionFactor;
    }

    // convert base unit to target unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

	@Override
	public double getConversionFactor() {
		// TODO Auto-generated method stub
		return conversionFactor;
	}

	@Override
	public double convertToBaseUnit(double value) {
		// TODO Auto-generated method stub
		return value*conversionFactor;
	}

	@Override
	public String getUnitName() {
		// TODO Auto-generated method stub
		return this.name();
	}

	
}