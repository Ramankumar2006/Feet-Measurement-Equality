import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityWeightTest {

    @Test
    void testEquality_KgToGram() {
        QuantityMeasurementApp.QuantityWeight q1 =
                new QuantityMeasurementApp.QuantityWeight(1.0, QuantityMeasurementApp.WeightUnit.KILOGRAM);

        QuantityMeasurementApp.QuantityWeight q2 =
                new QuantityMeasurementApp.QuantityWeight(1000.0, QuantityMeasurementApp.WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_KgToPound() {
        QuantityMeasurementApp.QuantityWeight q1 =
                new QuantityMeasurementApp.QuantityWeight(1.0, QuantityMeasurementApp.WeightUnit.KILOGRAM);

        QuantityMeasurementApp.QuantityWeight q2 =
                new QuantityMeasurementApp.QuantityWeight(2.20462, QuantityMeasurementApp.WeightUnit.POUND);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testConversion_KgToGram() {
        QuantityMeasurementApp.QuantityWeight q =
                new QuantityMeasurementApp.QuantityWeight(1.0, QuantityMeasurementApp.WeightUnit.KILOGRAM);

        assertEquals(1000.0, q.convertTo(QuantityMeasurementApp.WeightUnit.GRAM).getValue(), 0.01);
    }

    @Test
    void testConversion_PoundToKg() {
        QuantityMeasurementApp.QuantityWeight q =
                new QuantityMeasurementApp.QuantityWeight(2.20462, QuantityMeasurementApp.WeightUnit.POUND);

        assertEquals(1.0, q.convertTo(QuantityMeasurementApp.WeightUnit.KILOGRAM).getValue(), 0.01);
    }

    @Test
    void testAddition_CrossUnit() {
        QuantityMeasurementApp.QuantityWeight q1 =
                new QuantityMeasurementApp.QuantityWeight(1.0, QuantityMeasurementApp.WeightUnit.KILOGRAM);

        QuantityMeasurementApp.QuantityWeight q2 =
                new QuantityMeasurementApp.QuantityWeight(1000.0, QuantityMeasurementApp.WeightUnit.GRAM);

        assertEquals(2.0, q1.add(q2).getValue(), 0.01);
    }

    @Test
    void testAddition_TargetUnit() {
        QuantityMeasurementApp.QuantityWeight q1 =
                new QuantityMeasurementApp.QuantityWeight(1.0, QuantityMeasurementApp.WeightUnit.KILOGRAM);

        QuantityMeasurementApp.QuantityWeight q2 =
                new QuantityMeasurementApp.QuantityWeight(1000.0, QuantityMeasurementApp.WeightUnit.GRAM);

        assertEquals(2000.0,
                q1.add(q2, QuantityMeasurementApp.WeightUnit.GRAM).getValue(),
                0.01);
    }
}