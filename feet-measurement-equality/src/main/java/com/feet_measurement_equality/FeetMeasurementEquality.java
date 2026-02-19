package com.feet_measurement_equality;

import java.util.Objects;

public class QuantityMeasurementApp {
    enum LengthUnit {
        FEET(1.0),            // Base unit
        INCH(1.0/12.0);     // 1 inch = 1/12 feet
        private final double conversionFactorToFeet;
        LengthUnit(double conversionFactorToFeet) {
            this.conversionFactorToFeet = conversionFactorToFeet;
        }
        public double toFeet(double value) {
            return value*conversionFactorToFeet;
        }
    }
    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;
        public QuantityLength(double value, LengthUnit unit) {
            if (unit==null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value=value;
            this.unit=unit;
        }
        private double toFeet() {
            return unit.toFeet(value);
        }
        @Override
        public boolean equals(Object obj) {
            if (this==obj)
                return true;
            if (obj==null)
                return false;
            if (getClass()!=obj.getClass())
                return false;
            QuantityLength other=(QuantityLength) obj;
            return Double.compare(this.toFeet(), other.toFeet())==0;
        }
        @Override
        public int hashCode() {
            return Objects.hash(toFeet());
        }
        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }
    public static void main(String[] args) {
        QuantityLength q1=new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2=new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength q3=new QuantityLength(1.0, LengthUnit.INCH);
        System.out.println(q1 + " and "+q2+" Equal? "+q1.equals(q2));
        System.out.println(q3+" and "+q3+" Equal? "+q3.equals(q3));
    }
}
