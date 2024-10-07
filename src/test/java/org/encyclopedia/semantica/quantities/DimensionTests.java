package org.encyclopedia.semantica.quantities;

import org.encyclopedia.semantica.quantities.dimension.Dimension;
import org.encyclopedia.semantica.quantities.instances.Dimensions;
import org.encyclopedia.semantica.quantities.instances.Units;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DimensionTests {

    @Test
    public void initializeAll() {
        Dimensions.initialize();
    }

    @Test
    @DisplayName("Area * Length = Volume")
    public void testAreaLengthIsVolume() {
        Dimension volume = Dimensions.multiply(Dimensions.area, Dimensions.length);

        assertTrue(volume.equivalentTo(Dimensions.volume));
    }

    @Test
    @DisplayName("Area * Length = Length * Area")
    public void testCommutative() {
        Dimension volume1 = Dimensions.multiply(Dimensions.length, Dimensions.area);
        Dimension volume2 = Dimensions.multiply(Dimensions.area, Dimensions.length);

        assertTrue(volume1.equivalentTo(volume2));
    }

    @Test
    @DisplayName("Length * Length * Length = Volume")
    public void testLengthLengthLengthIsVolume() {
        Dimension volume = Dimensions.multiply(Dimensions.multiply(Dimensions.length, Dimensions.length), Dimensions.length);

        assertTrue(volume.equivalentTo(Dimensions.volume));
    }

}
