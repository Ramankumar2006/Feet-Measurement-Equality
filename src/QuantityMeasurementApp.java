package com.apps.quantitymeasurement;

import java.util.Objects;

/**
 * QuantityMeasurementApp - Final Implementation (UC1 - UC7)
 * Handles multiple length units, equality checks, unit conversion,
 * and advanced addition with explicit target units.
 */
public class QuantityMeasurementApp {

    // --- ENUM: Centralized Unit Factors (Base Unit: INCHES) ---
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

    // --- CLASS: Immutable QuantityLength Value Object ---
    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Measurement value must be a finite number.");
            }
            this.value = value;
            this.unit = Objects.requireNonNull(unit, "Unit cannot be null.");
        }

        public double getValue() { return value; }
        public LengthUnit getUnit() { return unit; }

        // --- ARITHMETIC API (UC6 & UC7) ---

        /** Implicit addition: returns result in the unit of the first operand. */
        public QuantityLength add(QuantityLength that) {
            return this.add(that, this.unit);
        }

        /** Explicit addition: returns result in a user-specified target unit. */
        public QuantityLength add(QuantityLength that, LengthUnit targetUnit) {
            if (that == null) throw new IllegalArgumentException("Second operand cannot be null.");
            if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null.");

            // Normalize to base unit (Inches), sum, and convert back
            double sumInBase = (this.value * this.unit.getConversionFactor()) +
                    (that.value * that.unit.getConversionFactor());

            double finalValue = sumInBase / targetUnit.getConversionFactor();

            // UC7: Precision rounding to 3 decimal places
            double roundedValue = Math.round(finalValue * 1000.0) / 1000.0;
            return new QuantityLength(roundedValue, targetUnit);
        }

        // --- EQUALITY CONTRACT (UC1, UC2, UC3, UC4) ---

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityLength that = (QuantityLength) obj;

            // Compare by base values with an epsilon tolerance for precision
            double thisBase = this.value * this.unit.getConversionFactor();
            double thatBase = that.value * that.unit.getConversionFactor();
            return Math.abs(thisBase - thatBase) < 0.01;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value * unit.getConversionFactor());
        }

        @Override
        public String toString() {
            return String.format("Quantity(%.3f, %s)", value, unit);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Quantity Measurement App: UC7 Final Run ---");
        QuantityLength oneYard = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength threeFeet = new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println("1 Yard + 3 Feet (Result in Feet): " + oneYard.add(threeFeet, LengthUnit.FEET));
        System.out.println("1 Yard + 3 Feet (Result in Yards): " + oneYard.add(threeFeet, LengthUnit.YARDS));
    }
}