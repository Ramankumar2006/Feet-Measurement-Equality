package com.apps.quantitymeasurement;

import java.util.Objects;

/**
 * QuantityMeasurementApp - UC6: Addition of Length Units
 * This implementation allows adding different length units and returns the
 * result in the unit of the first operand.
 */
public class QuantityMeasurementApp {

    // --- ENUM: Unit Definitions ---
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    // --- CLASS: QuantityLength (Immutable Value Object) ---
    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");
            this.value = value;
            this.unit = Objects.requireNonNull(unit, "Unit cannot be null");
        }

        public double getValue() { return value; }
        public LengthUnit getUnit() { return unit; }

        /**
         * Instance method: Adds another length to this one.
         * The result unit is inherited from 'this' object.
         */
        public QuantityLength add(QuantityLength that) {
            if (that == null) throw new IllegalArgumentException("Operand cannot be null");

            // Step 1: Convert both to base unit (Inches)
            double sumInBase = (this.value * this.unit.getConversionFactor()) +
                    (that.value * that.unit.getConversionFactor());

            // Step 2: Convert sum back to 'this' unit
            double finalValue = sumInBase / this.unit.getConversionFactor();

            return new QuantityLength(finalValue, this.unit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityLength that = (QuantityLength) obj;
            // Use 0.01 epsilon for CM precision handling
            return Math.abs((this.value * this.unit.getConversionFactor()) -
                    (that.value * that.unit.getConversionFactor())) < 0.01;
        }

        @Override
        public String toString() {
            return String.format("Quantity(%.2f, %s)", value, unit);
        }
    }

    // --- API DEMONSTRATION ---

    public static void demonstrateAddition(QuantityLength l1, QuantityLength l2) {
        QuantityLength result = l1.add(l2);
        System.out.println("Input:  Adding " + l1 + " and " + l2);
        System.out.println("Output: " + result + "\n");
    }

    public static void main(String[] args) {
        System.out.println("=== UC6: Addition of Length Units ===\n");

        // Feet + Feet
        demonstrateAddition(new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(2.0, LengthUnit.FEET));

        // Feet + Inches (1ft + 12in = 2ft)
        demonstrateAddition(new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES));

        // Inches + Feet (12in + 1ft = 24in)
        demonstrateAddition(new QuantityLength(12.0, LengthUnit.INCHES),
                new QuantityLength(1.0, LengthUnit.FEET));

        // Yards + Feet (1yd + 3ft = 2yd)
        demonstrateAddition(new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(3.0, LengthUnit.FEET));

        // Centimeters + Inches
        demonstrateAddition(new QuantityLength(2.54, LengthUnit.CENTIMETERS),
                new QuantityLength(1.0, LengthUnit.INCHES));
    }
}