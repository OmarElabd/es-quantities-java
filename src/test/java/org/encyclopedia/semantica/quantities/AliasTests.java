package org.encyclopedia.semantica.quantities;

import org.encyclopedia.semantica.quantities.instances.Dimensions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AliasTests {
    @Test
    public void testBaseDimensionAliases_RefEquals() {
        assertSame(Dimensions.L, Dimensions.length);
        assertSame(Dimensions.I, Dimensions.electricCurrent);
        assertSame(Dimensions.T, Dimensions.time);
        assertSame(Dimensions.O, Dimensions.temperature);
        assertSame(Dimensions.N, Dimensions.amountOfSubstance);
        assertSame(Dimensions.M, Dimensions.mass);
        assertSame(Dimensions.J, Dimensions.luminousIntensity);
    }

    @Test
    public void testBaseDimensionAliases_Equals() {
        assertEquals(Dimensions.L, Dimensions.length);
        assertEquals(Dimensions.I, Dimensions.electricCurrent);
        assertEquals(Dimensions.T, Dimensions.time);
        assertEquals(Dimensions.O, Dimensions.temperature);
        assertEquals(Dimensions.N, Dimensions.amountOfSubstance);
        assertEquals(Dimensions.M, Dimensions.mass);
        assertEquals(Dimensions.J, Dimensions.luminousIntensity);
    }
}
