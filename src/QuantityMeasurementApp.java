package com.apps.quantitymeasurement;

import java.util.Objects;

public class QuantityMeasurementApp {


    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            Feet feet = (Feet) obj;
            return Double.compare(feet.value, this.value) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }


    public static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            // Identical logic to Feet (Violates DRY principle as per UC2 disadvantage note)
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Inches inches = (Inches) obj;
            return Double.compare(inches.value, this.value) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }


    public static void demonstrateFeetEquality() {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        boolean result = feet1.equals(feet2);

        System.out.println("--- Feet Equality Check ---");
        System.out.println("Input: " + feet1.value + " ft and " + feet2.value + " ft");
        System.out.println("Output: Equal (" + result + ")\n");
    }

    public static void demonstrateInchesEquality() {
        Inches inch1 = new Inches(1.0);
        Inches inch2 = new Inches(1.0);
        boolean result = inch1.equals(inch2);

        System.out.println("--- Inches Equality Check ---");
        System.out.println("Input: " + inch1.value + " inch and " + inch2.value + " inch");
        System.out.println("Output: Equal (" + result + ")\n");
    }


    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();


        Feet f = new Feet(1.0);
        Inches i = new Inches(1.0);
        System.out.println("--- Cross-Type Check ---");
        System.out.println("Comparing 1.0 ft to 1.0 inch: Equal (" + f.equals(i) + ")");
    }
}