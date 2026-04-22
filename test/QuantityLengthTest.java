import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityLengthTest {

    @Test
    void testConvertFeetToInches() {
        QuantityMeasurementApp.QuantityLength q =
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength result =
                q.convertTo(QuantityMeasurementApp.LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), 0.01);
    }

    @Test
    void testEquality() {
        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testAddition() {
        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength result =
                q1.add(q2, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), 0.01);
    }

    @Test
    void testConvertToBaseUnit() {
        assertEquals(1.0,
                QuantityMeasurementApp.LengthUnit.INCHES.convertToBaseUnit(12.0),
                0.01);
    }

    @Test
    void testConvertFromBaseUnit() {
        assertEquals(12.0,
                QuantityMeasurementApp.LengthUnit.INCHES.convertFromBaseUnit(1.0),
                0.01);
    }
}