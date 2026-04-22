public class QuantityMeasurementApp {

    public enum WeightUnit {
        KILOGRAM(1.0),
        GRAM(0.001),
        POUND(0.453592);

        private final double factor;

        WeightUnit(double factor) {
            this.factor = factor;
        }

        public double convertToBaseUnit(double value) {
            return value * factor;
        }

        public double convertFromBaseUnit(double baseValue) {
            return baseValue / factor;
        }
    }

    static class QuantityWeight {
        private final double value;
        private final WeightUnit unit;
        private static final double EPSILON = 0.0001;

        public QuantityWeight(double value, WeightUnit unit) {
            if (unit == null) throw new IllegalArgumentException();
            if (Double.isNaN(value) || Double.isInfinite(value)) throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public QuantityWeight convertTo(WeightUnit targetUnit) {
            double base = unit.convertToBaseUnit(value);
            double converted = targetUnit.convertFromBaseUnit(base);
            return new QuantityWeight(converted, targetUnit);
        }

        public QuantityWeight add(QuantityWeight other) {
            return add(other, this.unit);
        }

        public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {
            double base1 = unit.convertToBaseUnit(value);
            double base2 = other.unit.convertToBaseUnit(other.value);
            double sum = base1 + base2;
            double result = targetUnit.convertFromBaseUnit(sum);
            return new QuantityWeight(result, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;
            QuantityWeight other = (QuantityWeight) obj;
            double base1 = unit.convertToBaseUnit(value);
            double base2 = other.unit.convertToBaseUnit(other.value);
            return Math.abs(base1 - base2) < EPSILON;
        }
    }

    public static void main(String[] args) {
        QuantityWeight q1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight q2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        System.out.println(q1.equals(q2));
        System.out.println(q1.convertTo(WeightUnit.GRAM).getValue());
        System.out.println(q1.add(q2).getValue());
    }
}