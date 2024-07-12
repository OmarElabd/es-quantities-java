package org.encyclopedia.semantica.quantities.equivalence;

import org.encyclopedia.semantica.quantities.model.derived.DivisionDerivedUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.encyclopedia.semantica.quantities.instances.SIDerivedUnits.*;
import static org.encyclopedia.semantica.quantities.instances.SIDerivedUnits.metrePerSecond;
import static org.encyclopedia.semantica.quantities.instances.SIUnits.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class InequivalenceTests {
    @Test
    public void testInequivalence_Power() {
        assertFalse(metre.equivalentTo(squareMetre));
    }

    @Test
    public void testInequivalence_NamedUnits() {
        assertFalse(joule.equivalentTo(watt.getDerivation()));
    }

    @Test
    public void testInequivalence_Prefix() {
        assertFalse(centimetre.equivalentTo(metre));
        assertFalse(kilogram.equivalentTo(gram));
    }

    @Test
    @DisplayName("m/s != m/s^2")
    public void testInequivalence_FailureMultiplePer() {
        DivisionDerivedUnit metrePerSecondSquaredDerived = new DivisionDerivedUnit(metrePerSecond, second);

        assertFalse(metrePerSecond.equivalentTo(metrePerSecondSquaredDerived));
    }

    @Test
    public void testInequivalence_DerivedPrefix() {
        // assertFalse();
    }

}
