package org.encyclopedia.semantica.quantities;

import org.encyclopedia.semantica.quantities.instances.*;
import org.encyclopedia.semantica.quantities.model.Unit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InitializationTests {
    @Test
    public void initializeAll() {
        Units.initialize();
    }

    @Test
    public void initializeCGS() {
        List<Unit> units = CGSUnits.All;
        assertTrue(units.size() > 0);
    }

    @Test
    public void initializeSI() {
        List<Unit> units = SIUnits.All;
        assertTrue(units.size() > 0);
    }

    @Test
    public void initializeSIDerived() {
        List<Unit> units = SIDerivedUnits.All;
        assertTrue(units.size() > 0);
    }

    @Test
    public void initializeUSCustomary() {
        List<Unit> units = USCustomaryUnits.All;
        assertTrue(units.size() > 0);
    }


//
//    @Test
//    public void initializeImperial() {
//        List<Unit> units = ImperialUnits.All;
//        assertTrue(units.size() > 0);
//    }
//
//    @Test
//    public void initializeISOUnits() {
//        List<Unit> units = ISOUnits.All;
//        assertTrue(units.size() > 0);
//    }
//
//    @Test
//    public void initializeNonSIUnits() {
//        List<Unit> units = NonSIUnits.All;
//        assertTrue(units.size() > 0);
//    }
}
