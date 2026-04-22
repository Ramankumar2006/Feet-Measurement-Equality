package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.apps.quantitymeasurement.QuantityMeasurementApp.LengthUnit;
import com.apps.quantitymeasurement.QuantityMeasurementApp.QuantityLength;

public class QuantityMeasurementAppTest {

    // --- EQUALITY TESTS (UC1 - UC4) ---
    @Test
    public void testEquality_SameUnit_SameValue() {
        assertEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(1.0, LengthUnit.FEET));
    }

    @Test
    public void testEquality_CrossUnit_FeetToInches() {
        assertEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES));
    }

    @Test
    public void testEquality_CentimetersToInches() {
        assertEquals(new QuantityLength(2.54, LengthUnit.CENTIMETERS), new QuantityLength(1.0, LengthUnit.INCHES));
    }

    // --- ARITHMETIC TESTS (UC6 - UC7) ---
    @Test
    public void testAddition_ImplicitTargetUnit() {
        QuantityLength oneFoot = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength twelveInches = new QuantityLength(12.0, LengthUnit.INCHES);

        // Result unit should default to first operand unit (FEET)
        QuantityLength result = oneFoot.add(twelveInches);
        assertEquals(2.0, result.getValue());
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {
        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);

        // 3ft + 1yd = 2 yards
        QuantityLength result = feet.add(yard, LengthUnit.YARDS);
        assertEquals(2.0, result.getValue());
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testAddition_CentimetersAndInches_ToCentimeters() {
        QuantityLength cm = new QuantityLength(2.54, LengthUnit.CENTIMETERS);
        QuantityLength inch = new QuantityLength(1.0, LengthUnit.INCHES);

        // 2.54cm + 1in = 5.08 cm
        QuantityLength result = cm.add(inch, LengthUnit.CENTIMETERS);
        assertEquals(5.08, result.getValue(), 0.01);
    }

    @Test
    public void testAddition_Commutativity() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength sum1 = l1.add(l2, LengthUnit.YARDS);
        QuantityLength sum2 = l2.add(l1, LengthUnit.YARDS);

        assertEquals(sum1, sum2);
    }

    // --- NEGATIVE/BOUNDARY TESTS ---
    @Test
    public void testInvalidInput_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityLength(Double.NaN, LengthUnit.FEET));
    }

    @Test
    public void testNullTargetUnit_ThrowsException() {
        QuantityLength length = new QuantityLength(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> length.add(length, null));
    }
}