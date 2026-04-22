package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // Inner class to represent Feet measurement
    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            // 1. Reference Check
            if (this == obj) return true;

            // 2. Null Check & 3. Type Check
            if (obj == null || getClass() != obj.getClass()) return false;

            // 4. Value Comparison
            Feet feet = (Feet) obj;
            return Double.compare(feet.value, this.value) == 0;
        }
    }

    public static void main(String[] args) {
        Feet firstFeet = new Feet(1.0);
        Feet secondFeet = new Feet(1.0);

        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + firstFeet.equals(secondFeet) + ")");
    }
}