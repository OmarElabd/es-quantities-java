package org.encyclopedia.semantica.quantities.parser.units;

import org.encyclopedia.semantica.quantities.instances.SIDerivedUnits;
import org.encyclopedia.semantica.quantities.instances.Units;
import org.encyclopedia.semantica.quantities.model.PrefixedUnit;
import org.encyclopedia.semantica.quantities.model.Unit;
import org.encyclopedia.semantica.quantities.model.derived.DivisionDerivedUnit;
import org.encyclopedia.semantica.quantities.model.derived.PowerDerivedUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.encyclopedia.semantica.quantities.instances.Prefixes.kilo;
import static org.encyclopedia.semantica.quantities.instances.SIUnits.metre;
import static org.encyclopedia.semantica.quantities.instances.SIUnits.second;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SymbolParserTests {
    @Test
    @DisplayName("m")
    public void testParse_SingleSymbol() throws ParseException {
        String str = "m";

        Unit expected = metre;
        Unit actual = Units.parseSymbol(str);

        assertTrue(expected.equivalentTo(actual));
    }

    @Test
    @DisplayName("m/s")
    public void testParse_Division() throws ParseException {
        String str = "m/s";

        Unit expected = SIDerivedUnits.metrePerSecond;
        Unit actual = Units.parseSymbol(str);

        assertTrue(expected.equivalentTo(actual));
    }

    @Test
    @DisplayName("m/s^2")
    public void testParse_DivisionAndPower() throws ParseException {
        String str = "m/s^2";

        Unit expected = SIDerivedUnits.metrePerSecondSquared;
        Unit actual = Units.parseSymbol(str);

        assertTrue(expected.equivalentTo(actual));
    }

    @Test
    @DisplayName("N m")
    public void testParse_Product() throws ParseException {
        String str = "N m";

        Unit expected = SIDerivedUnits.newtonMetre;
        Unit actual = Units.parseSymbol(str);

        assertTrue(expected.equivalentTo(actual));
    }

    @Test
    @DisplayName("N m s")
    public void testParse_MultipleProduct() throws ParseException {
        String str = "N m s";

        Unit expected = SIDerivedUnits.newtonMetreSecond;
        Unit actual = Units.parseSymbol(str);

        assertTrue(expected.equivalentTo(actual));
    }

    @Test
    @DisplayName("m² (existing)")
    public void testParse_UnicodePowerSimple_Existing() throws ParseException {
        String str = "m²";

        Unit expected = SIDerivedUnits.squareMetre;
        Unit actual = Units.parseSymbol(str);

        assertTrue(expected.equivalentTo(actual));
    }

    @Test
    @DisplayName("m⁷ (non-existing)")
    public void testParse_UnicodePower_SimpleNonExisting() throws ParseException {
        String str = "m⁷";

        Unit expected = new PowerDerivedUnit(metre, 7);
        Unit actual = Units.parseSymbol(str);

        assertTrue(expected.equivalentTo(actual));
    }

    @Test
    @DisplayName("m/s² (existing)")
    public void testParse_UnicodeSymbol_ComplexExisting() throws ParseException {
        String str = "m/s²";

        Unit expected = SIDerivedUnits.metrePerSecondSquared;
        Unit actual = Units.parseSymbol(str);

        assertTrue(expected.equivalentTo(actual));
    }

    @Test
    @DisplayName("m/s⁷ (non-existing)")
    public void testUnicodeSymbolAndPower_NonExisting() throws ParseException {
        String str = "m/s⁷";

        Unit expected = new DivisionDerivedUnit(metre, new PowerDerivedUnit(second, 7));
        Unit actual = Units.parseSymbol(str);

        assertTrue(expected.equivalentTo(actual));
    }

    @Test
    @DisplayName("km")
    public void testUnicodeSymbolPrefix_Simple() throws ParseException {
        String str = "km";

        Unit expected = new PrefixedUnit(kilo, metre);
        Unit actual = Units.parseSymbol(str);

        assertTrue(expected.equivalentTo(actual));
    }

    @Test
    @DisplayName("km/s⁷")
    public void testUnicodeSymbolPrefix_Complex() throws ParseException {
        String str = "km/s⁷";

        PrefixedUnit kilometre = new PrefixedUnit(kilo, metre);
        Unit expected = new DivisionDerivedUnit(kilometre, new PowerDerivedUnit(second, 7));
        Unit actual = Units.parseSymbol(str);

        assertTrue(expected.equivalentTo(actual));
    }

}
