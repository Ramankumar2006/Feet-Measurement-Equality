import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityLengthTest {

    @Test
    void testFeetToFeetEquality() {
        assertTrue(new QuantityLength(1.0, LengthUnit.FEET)
                .equals(new QuantityLength(1.0, LengthUnit.FEET)));
    }

    @Test
    void testInchToInchEquality() {
        assertTrue(new QuantityLength(1.0, LengthUnit.INCHES)
                .equals(new QuantityLength(1.0, LengthUnit.INCHES)));
    }

    @Test
    void testFeetToInchEquality() {
        assertTrue(new QuantityLength(1.0, LengthUnit.FEET)
                .equals(new QuantityLength(12.0, LengthUnit.INCHES)));
    }

    @Test
    void testInchToFeetEquality() {
        assertTrue(new QuantityLength(12.0, LengthUnit.INCHES)
                .equals(new QuantityLength(1.0, LengthUnit.FEET)));
    }

    @Test
    void testDifferentValues() {
        assertFalse(new QuantityLength(1.0, LengthUnit.FEET)
                .equals(new QuantityLength(2.0, LengthUnit.FEET)));
    }

    @Test
    void testNullComparison() {
        assertFalse(new QuantityLength(1.0, LengthUnit.FEET).equals(null));
    }

    @Test
    void testSameReference() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);
        assertTrue(q.equals(q));
    }

    @Test
    void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null));
    }
}