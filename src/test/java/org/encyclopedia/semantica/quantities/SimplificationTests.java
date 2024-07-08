package org.encyclopedia.semantica.quantities;

import org.encyclopedia.semantica.quantities.model.Unit;
import org.encyclopedia.semantica.quantities.model.derived.DivisionDerivedUnit;
import org.encyclopedia.semantica.quantities.model.derived.PowerDerivedUnit;
import org.encyclopedia.semantica.quantities.model.derived.ProductDerivedUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.encyclopedia.semantica.quantities.instances.SIDerivedUnits.*;
import static org.encyclopedia.semantica.quantities.instances.SIUnits.metre;
import static org.encyclopedia.semantica.quantities.instances.SIUnits.second;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimplificationTests {
    @Test
    @DisplayName("m^2 / m => m")
    public void testSimplifyToSingle() {
        Unit unit = new DivisionDerivedUnit(squareMetre, metre);

        String actual = unit.getSimplifiedForm().getName();

        assertEquals("metre", actual);
    }

    @Test
    @DisplayName("(J^2) / J => J")
    public void testSimplifyToCoherent() {
        Unit unit = new DivisionDerivedUnit(new PowerDerivedUnit(joule, 2), joule);

        String actual = unit.getSimplifiedForm().getName();

        assertEquals("joule", actual);
    }

    @Test
    @DisplayName("(J/m) * m => J")
    public void testSimplifyMultiplicationSingle() {
        Unit joulePerMetre = new DivisionDerivedUnit(joule, metre);
        Unit unit = new ProductDerivedUnit(joulePerMetre, metre);

        String actual = unit.getSimplifiedForm().getName();

        assertEquals("joule", actual);
    }

    @Test
    @DisplayName("(J/m) / m => J/m^2")
    public void testSimplifyDivisionBaseSingle() {
        Unit joulePerMetre = new DivisionDerivedUnit(joule, metre);
        Unit unit = new DivisionDerivedUnit(joulePerMetre, metre);

        String actual = unit.getSimplifiedForm().getName();

        assertEquals("joule per square metre", actual);
    }

    @Test
    @DisplayName("J / m => J/m")
    public void testSimplifyNormal() {
        Unit unit = new DivisionDerivedUnit(joule, metre);

        String actual = unit.getSimplifiedForm().getName();

        assertEquals("joule per metre", actual);
    }

    @Test
    @DisplayName("N m s / s => N m")
    public void testSimplifyDivisionMultiple() {
        Unit unit = new DivisionDerivedUnit(newtonMetreSecond, second);

        String actual = unit.getSimplifiedForm().getName();

        assertEquals("newton metre", actual);
    }

    // TODO Revise
    @Test
    @DisplayName("(m/s) / m => s^-1")
    public void testSimplifyDivisionReciprocal() {
        Unit unit = new DivisionDerivedUnit(metrePerSecond, metre);

        String actual = unit.getSimplifiedForm().getName();

        assertEquals("reciprocal second", actual);
    }
}
