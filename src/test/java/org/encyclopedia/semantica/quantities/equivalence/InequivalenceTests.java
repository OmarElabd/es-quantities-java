package org.encyclopedia.semantica.quantities.equivalence;

import org.junit.jupiter.api.Test;

import static org.encyclopedia.semantica.quantities.instances.SIDerivedUnits.joule;
import static org.encyclopedia.semantica.quantities.instances.SIDerivedUnits.squareMetre;
import static org.encyclopedia.semantica.quantities.instances.SIDerivedUnits.watt;
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
    public void testInequivalence_DerivedPrefix() {
        // assertFalse();
    }

}
