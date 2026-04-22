public class QuantityMeasurementApp {

    public enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(1.0 / 30.48);

        private final double factor;

        LengthUnit(double factor) {
            this.factor = factor;
        }

        public double convertToBaseUnit(double value) {
            return value * factor;
        }

        public double convertFromBaseUnit(double baseValue) {
            return baseValue / factor;
        }
    }

    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;
        private static final double EPSILON = 0.0001;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException();
            if (Double.isNaN(value) || Double.isInfinite(value)) throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public QuantityLength convertTo(LengthUnit targetUnit) {
            double base = unit.convertToBaseUnit(value);
            double converted = targetUnit.convertFromBaseUnit(base);
            return new QuantityLength(converted, targetUnit);
        }

        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            double base1 = unit.convertToBaseUnit(value);
            double base2 = other.unit.convertToBaseUnit(other.value);
            double sum = base1 + base2;
            double result = targetUnit.convertFromBaseUnit(sum);
            return new QuantityLength(result, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof QuantityLength)) return false;
            QuantityLength other = (QuantityLength) obj;
            double base1 = unit.convertToBaseUnit(value);
            double base2 = other.unit.convertToBaseUnit(other.value);
            return Math.abs(base1 - base2) < EPSILON;
        }
    }

    public static void main(String[] args) {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println(q1.convertTo(LengthUnit.INCHES).getValue());
        System.out.println(q1.add(q2, LengthUnit.FEET).getValue());
        System.out.println(q1.equals(q2));
    }
}