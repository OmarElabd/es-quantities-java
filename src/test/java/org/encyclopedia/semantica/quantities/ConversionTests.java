package org.encyclopedia.semantica.quantities;

import org.encyclopedia.semantica.quantities.instances.SIDerivedUnits;
import org.encyclopedia.semantica.quantities.instances.SIUnits;
import org.encyclopedia.semantica.quantities.instances.TemperatureUnits;
import org.encyclopedia.semantica.quantities.model.derived.DivisionDerivedUnit;
import org.encyclopedia.semantica.quantities.model.derived.PowerDerivedUnit;
import org.encyclopedia.semantica.quantities.model.derived.ProductDerivedUnit;
import org.encyclopedia.semantica.quantities.model.PrefixedUnit;
import org.encyclopedia.semantica.quantities.model.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.encyclopedia.semantica.quantities.instances.CGSUnits.erg;
import static org.encyclopedia.semantica.quantities.instances.ISOUnits.bit;
import static org.encyclopedia.semantica.quantities.instances.ISOUnits.bytes;
import static org.encyclopedia.semantica.quantities.instances.ImperialUnits.foot;
import static org.encyclopedia.semantica.quantities.instances.ImperialUnits.inch;
import static org.encyclopedia.semantica.quantities.instances.ImperialUnits.squareInch;
import static org.encyclopedia.semantica.quantities.instances.Prefixes.kibi;
import static org.encyclopedia.semantica.quantities.instances.Prefixes.kilo;
import static org.encyclopedia.semantica.quantities.instances.Prefixes.milli;
import static org.encyclopedia.semantica.quantities.instances.SIDerivedUnits.*;
import static org.encyclopedia.semantica.quantities.instances.SIUnits.*;
import static org.encyclopedia.semantica.quantities.instances.TemperatureUnits.*;
import static org.encyclopedia.semantica.quantities.instances.USCustomaryUnits.squareFoot;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConversionTests {
    private final PrefixedUnit millimetre = new PrefixedUnit(milli, metre);
    private final PrefixedUnit kilometre = new PrefixedUnit(kilo, metre);
    private final PrefixedUnit millijoule = new PrefixedUnit(milli, SIDerivedUnits.joule);
    private final PrefixedUnit kilojoule = new PrefixedUnit(kilo, SIDerivedUnits.joule);
    private final PowerDerivedUnit square_millimetre = new PowerDerivedUnit(millimetre, 2);
    private final PowerDerivedUnit square_kilometre = new PowerDerivedUnit(kilometre, 2);

    @Test
    @DisplayName("1,000,000 mm = 1 km")
    public void testPrefixPositiveHighToNegativeLow() {
        Quantity quantity = new Quantity(1000000, millimetre);
        Quantity converted = quantity.convertTo(kilometre);

        assertEquals(1.0, converted.value);
    }

    @Test
    @DisplayName("1,000,000 mJ = 1 kJ")
    public void testPrefixNamedDerivedPositiveHighToNegativeLow() {
        Quantity quantity = new Quantity(1000000, millijoule);
        Quantity converted = quantity.convertTo(kilojoule);

        assertEquals(1.0, converted.value);
    }

    @Test
    @DisplayName("10,000 g = 10 kg")
    public void testPrefixZeroToPositiveHigh() {
        Quantity quantity = new Quantity(10000, gram);
        Quantity converted = quantity.convertTo(SIUnits.kilogram);

        assertEquals(10.0, converted.value);
    }

    @Test
    @DisplayName("1 m^2 = 1,000,000 mm^2")
    public void testPrefixSquareLowToSquareHigh() {
        Quantity quantity = new Quantity(1, squareMetre);
        Quantity converted = quantity.convertTo(square_millimetre);

        assertEquals(1000000.0, converted.value);
    }

    @Test
    @DisplayName("1,000 m/s = 1 km/s")
    public void testPrefixDivisionNumerator() {
        Unit kilometrePerSecond = new DivisionDerivedUnit(kilometre, second);
        Quantity quantity = new Quantity(1000, metrePerSecond);

        Quantity converted = quantity.convertTo(kilometrePerSecond);

        assertEquals(1.0, converted.value);
    }

    @Test
    @DisplayName("0.0001 kg/m^2 = 100,000 g/km^2")
    public void testPrefixDivisionComplex() {
        Unit gramPerSquareKilometer = new DivisionDerivedUnit(gram, square_kilometre);
        Quantity quantity = new Quantity(0.0001, kilogramPerSquareMetre);

        Quantity converted = quantity.convertTo(gramPerSquareKilometer);

        assertEquals(100000.0, converted.value);
    }

    @Test
    @DisplayName("1 ft = 12 in")
    public void testConversion() {
        Quantity quantity = new Quantity(1, foot);

        Quantity converted = quantity.convertTo(inch);

        assertEquals(12.0, converted.value);
    }

    @Test
    @DisplayName("1 ft^2 = 144 in^2")
    public void testConversionWithPower() {
        Quantity quantity = new Quantity(1, squareFoot);

        Quantity converted = quantity.convertTo(squareInch);

        assertEquals(144.0, converted.value);
    }

    @Test
    @DisplayName("100,000 erg = 0.01 J")
    public void testSIVsCGS() {
        Quantity quantity = new Quantity(100000, erg);

        Quantity converted = quantity.convertTo(joule);

        assertEquals(0.01, converted.value);
    }

    @Test
    @DisplayName("21.98 J = 21.98 J")
    public void testSameUnitConversion() {
        Quantity quantity = new Quantity(21.98, joule);
        Quantity converted = quantity.convertTo(joule);

        assertEquals(quantity.value, converted.value);
    }

    @Test
    @DisplayName("14.2 m/s^2 = 14.2 (m/s) * s^-1")
    public void testSameUnitDifferentDerivation() {
        DivisionDerivedUnit mpsDivisionDerived = new DivisionDerivedUnit(metre, squareSecond);
        ProductDerivedUnit mpsProductDerived = new ProductDerivedUnit(metrePerSecond, reciprocalSecond);

        Quantity quantity = new Quantity(14.2, mpsDivisionDerived);
        Quantity converted = quantity.convertTo(mpsProductDerived);

        assertEquals(quantity.value, converted.value);
    }

    @Test
    public void testDerivedDimensionVsSpecifiedDimension() {
        ProductDerivedUnit mpsProductDerived = new ProductDerivedUnit(metrePerSecond, reciprocalSecond);

        Quantity quantity = new Quantity(14.2, metrePerSecond);
        Quantity converted = quantity.convertTo(mpsProductDerived);

        assertEquals(quantity.value, converted.value);
    }

    @Test
    @DisplayName("200 K = -73.15 degC")
    public void testTemperature_KtoC() {
        Quantity quantity = new Quantity(200.0, kelvin);
        Quantity converted = quantity.convertTo(celsius);

        assertEquals(-73.15, converted.value.doubleValue(), 0.1);
    }

    @Test
    @DisplayName("-40 degF = -40 degC")
    public void testTemperature_CtoF_Intersection() {
        Quantity quantityC = new Quantity(-40.0, celsius);
        Quantity quantityF = new Quantity(-40.0, fahrenheit);

        assertEquals(quantityC.value, quantityC.convertTo(fahrenheit).value);
        assertEquals(quantityF.value, quantityF.convertTo(celsius).value);
    }

    @Test
    public void testTemperature_C() {
        Quantity quantity = new Quantity(500, celsius);

        assertEquals(773.15, quantity.convertTo(kelvin).doubleValue(), 0.01);
        assertEquals(932.00, quantity.convertTo(fahrenheit).doubleValue(), 0.01);
        assertEquals(1391.67, quantity.convertTo(rankine).doubleValue(), 0.01);
        // assertEquals(-600.00, quantity.convertTo(delisle).getDoubleValue(), 0.1);
        assertEquals(165.00, quantity.convertTo(TemperatureUnits.newton).doubleValue(), 0.01);
        assertEquals(400.00, quantity.convertTo(reaumur).doubleValue(), 0.01);
        assertEquals(270.00, quantity.convertTo(romer).doubleValue(), 0.01);
    }

    @Test
    public void testTemperature_Inference() {
        Quantity quantity = new Quantity(932.00, fahrenheit);

        assertEquals(773.15, quantity.convertTo(kelvin).doubleValue(), 0.02);
        assertEquals(1391.67, quantity.convertTo(rankine).doubleValue(), 0.01);
        //assertEquals(-600.00, quantity.convertTo(delisle).doubleValue(), 0.1);
        assertEquals(165.00, quantity.convertTo(TemperatureUnits.newton).doubleValue(), 0.01);
        assertEquals(400.00, quantity.convertTo(reaumur).doubleValue(), 0.01);
        assertEquals(270.00, quantity.convertTo(romer).doubleValue(), 0.01);
    }

    @Test
    @DisplayName("1 byte = 8 bits")
    public void testConvertBinary() {
        Quantity quantity = new Quantity(1, bytes);

        assertEquals(8, quantity.convertTo(bit).intValue());
    }

    @Test
    @DisplayName("1 kilobit = 125 bytes")
    public void testConvertBinarySIPrefix() {
        Unit kibiBit = new PrefixedUnit(kilo, bit);
        Quantity quantity = new Quantity(1, kibiBit);

        assertEquals(125, quantity.convertTo(bytes).intValue());
    }

    @Test
    @DisplayName("1 kibibit = 128 bytes")
    public void testConvertBinaryIECPrefix() {
        Unit kibiBit = new PrefixedUnit(kibi, bit);
        Quantity quantity = new Quantity(1, kibiBit);

        assertEquals(128, quantity.convertTo(bytes).intValue());
    }

    @Test
    @DisplayName("1 foot = 304.8 mm")
    public void testConvertImperialToPrefix() {
        Unit millimeter = new PrefixedUnit(milli, meter);
        Quantity quantity = new Quantity(1, foot);

        assertEquals(304.8, quantity.convertTo(millimeter).intValue());
    }

    @Test
    @DisplayName("1 mm = 304.8 mm")
    public void testConvertPrefixToImperial() {
        Unit millimeter = new PrefixedUnit(milli, meter);
        Quantity quantity = new Quantity(1, millimeter);

        assertEquals(0.00328084, quantity.convertTo(foot).intValue());
    }

    @Test
    @DisplayName("100 Km/s = x Miles/hour")
    public void testConvertComplex() {

    }
}
