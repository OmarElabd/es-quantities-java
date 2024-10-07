package org.encyclopedia.semantica.quantities.parser.units;

import org.encyclopedia.semantica.quantities.instances.SIDerivedUnits;
import org.encyclopedia.semantica.quantities.instances.Units;
import org.encyclopedia.semantica.quantities.model.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTests {

    @Test
    @DisplayName("centimetres per second squared -> cm/s^2")
    public void testParse_Prefix_Long_Plural() throws ParseException {
        String str = "centimetres per second squared";

        Unit expected = SIDerivedUnits.metrePerSecondSquared;
        Unit actual = Units.parseName(str);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("metres per second per second -> m/s/s")
    public void testParse_TwoPer() throws ParseException {
        String str = "centimetres per second per second";

        Unit expected = SIDerivedUnits.metrePerSecondSquared;
        Unit actual = Units.parseName(str);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("centimetres per second per second -> cm/s/s")
    public void testParse_Prefix_TwoPer() throws ParseException {
        String str = "centimetres per second per second";

        Unit expected = SIDerivedUnits.metrePerSecondSquared;
        Unit actual = Units.parseName(str);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("centi-metres per second squared -> cm/s^2")
    public void testParse_DashPrefix_LongPlural() throws ParseException {
        String str = "centi-metres per second squared";

        Unit expected = SIDerivedUnits.metrePerSecondSquared;
        Unit actual = Units.parseName(str);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("metre per second squared -> m/s^2")
    public void testParse_LongSingular() throws ParseException {
        String str = "metre per second squared";

        Unit expected = SIDerivedUnits.metrePerSecondSquared;
        Unit actual = Units.parseName(str);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("centimetre per second squared -> cm/s^2")
    public void testParse_Prefix_LongSingular() throws ParseException {
        String str = "centimetre per second squared";

        Unit expected = SIDerivedUnits.metrePerSecondSquared;
        Unit actual = Units.parseName(str);

        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("centi-metre per second squared -> cm/s^2")
    public void testParse_DashPrefix_LongSingular() throws ParseException {
        String str = "centi-metre per second squared";

        Unit expected = SIDerivedUnits.metrePerSecondSquared;
        Unit actual = Units.parseName(str);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("metres per second squared")
    public void testParse_LongPlural() throws ParseException {
        String str = "metres per second squared";

        Unit expected = SIDerivedUnits.metrePerSecondSquared;
        Unit actual = Units.parseName(str);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("meter per second squared")
    public void testParse_LongSingularAlt() throws ParseException {
        String str = "meter per second squared";

        Unit expected = SIDerivedUnits.metrePerSecondSquared;
        Unit actual = Units.parseName(str);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("metres per second squared -> m/s^2")
    public void testParse_LongPluralAlt() throws ParseException {
        String str = "meters per second squared";

        Unit expected = SIDerivedUnits.metrePerSecondSquared;
        Unit actual = Units.parseName(str);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("centimeters per second squared -> cm/s^2")
    public void testParse_Prefix_LongPluralAlt() throws ParseException {
        String str = "centimeters per second squared";

        Unit expected = SIDerivedUnits.metrePerSecondSquared;
        Unit actual = Units.parseName(str);

        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("centi-meters per second squared -> cm/s^2")
    public void testParse_DashPrefix_LongPluralAlt() throws ParseException {
        String str = "centi-meters per second squared";

        Unit expected = SIDerivedUnits.metrePerSecondSquared;
        Unit actual = Units.parseName(str);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("metre_per_second_squared -> m/s^2")
    public void testParse_Url() throws ParseException {
        String str = "metre_per_second_squared";

        Unit expected = SIDerivedUnits.metrePerSecondSquared;
        Unit actual = Units.parseName(str);

        assertEquals(expected, actual);
    }
}
