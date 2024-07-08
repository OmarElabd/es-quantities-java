package org.encyclopedia.semantica.quantities;

import org.encyclopedia.semantica.quantities.model.derived.PowerDerivedUnit;
import org.encyclopedia.semantica.quantities.model.derived.ProductDerivedUnit;
import org.encyclopedia.semantica.quantities.model.derived.ReciprocalDerivedUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.encyclopedia.semantica.quantities.instances.SIDerivedUnits.*;
import static org.encyclopedia.semantica.quantities.instances.SIUnits.metre;
import static org.encyclopedia.semantica.quantities.instances.SIUnits.second;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NameTests {
    @Test
    @DisplayName("cubic metre")
    public void testPower() {
        assertEquals("cubic metre", cubicMetre.getName());
    }

    @Test
    @DisplayName("reciprocal second")
    public void testReciprocalSingle() {
        assertEquals("reciprocal second", reciprocalSecond.getName());
    }

    @Test
    @DisplayName("reciprocal pascal second")
    public void testReciprocalMultiple() {
        ProductDerivedUnit unit = new ProductDerivedUnit(new ReciprocalDerivedUnit(pascal), reciprocalSecond);
        assertEquals("reciprocal pascal second", unit.getName());
    }

    @Test
    @DisplayName("reciprocal square metre")
    public void testReciprocalPowerSingle() {
        PowerDerivedUnit unit = new PowerDerivedUnit(metre, -2);
        assertEquals("reciprocal square metre", unit.getName());
    }

    @Test
    @DisplayName("reciprocal pascal square second")
    public void testReciprocalPowerMultiple() {
        ReciprocalDerivedUnit reciprocalPascal = new ReciprocalDerivedUnit(pascal);
        PowerDerivedUnit reciprocalSquareSecond = new PowerDerivedUnit(second, -2);
        ProductDerivedUnit unit = new ProductDerivedUnit(reciprocalPascal, reciprocalSquareSecond);

        assertEquals("reciprocal pascal square second", unit.getName());
    }
}
