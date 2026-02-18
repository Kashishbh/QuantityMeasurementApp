package com.feet_measurement_equality;

// UC2-FeetAndInchesMeasurementEquality

public class FeetMeasurementEquality {
	static class Feet {
        private final double value;
        public Feet(double value) {
            this.value = value;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }
    static class Inches {
        private final double value;
        public Inches(double value) {
            this.value = value;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }
        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }
    // Static method for Feet comparison
    public static boolean checkFeetEquality(double value1, double value2) {
        Feet first = new Feet(value1);
        Feet second = new Feet(value2);
        return first.equals(second);
    }
    // Static method for Inches comparison
    public static boolean checkInchesEquality(double value1, double value2) {
        Inches first = new Inches(value1);
        Inches second = new Inches(value2);
        return first.equals(second);
    }
    public static void main(String[] args) {
        boolean feetResult = checkFeetEquality(1.0, 1.0);
        boolean inchResult = checkInchesEquality(1.0, 1.0);
        System.out.println("Feet equal? " + feetResult);
        System.out.println("Inches equal? " + inchResult);
    }

}
