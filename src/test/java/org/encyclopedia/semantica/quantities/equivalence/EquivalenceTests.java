package org.encyclopedia.semantica.quantities.equivalence;

import org.encyclopedia.semantica.quantities.model.derived.DivisionDerivedUnit;
import org.encyclopedia.semantica.quantities.model.derived.PowerDerivedUnit;
import org.encyclopedia.semantica.quantities.model.derived.ProductDerivedUnit;
import org.encyclopedia.semantica.quantities.common.Normalization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.encyclopedia.semantica.quantities.instances.SIDerivedUnits.*;
import static org.encyclopedia.semantica.quantities.instances.SIUnits.metre;
import static org.encyclopedia.semantica.quantities.instances.SIUnits.second;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EquivalenceTests {
    @Test
    public void testEquivalence_SIVsCGS() {

    }

    @Test
    public void testEquivalence_CancelledUnits() {
        ProductDerivedUnit metreDerived = new ProductDerivedUnit(metrePerSecond, second);

        assertTrue(metre.equivalentTo(metreDerived));
    }

    @Test
    @DisplayName("m/s/s == m/s^2")
    public void testEquivalence_MultiplePer() {
        DivisionDerivedUnit metrePerSecondSquaredDerived = new DivisionDerivedUnit(metrePerSecond, second);

        assertTrue(metrePerSecondSquared.equivalentTo(metrePerSecondSquaredDerived));
    }

    @Test
    public void testEquivalence_DivisionVsProduct() {
        // metre per second squared
        DivisionDerivedUnit mpsDivisionDerived = new DivisionDerivedUnit(metre, squareSecond);
        ProductDerivedUnit mpsProductDerived = new ProductDerivedUnit(metrePerSecond, reciprocalSecond);

        assertTrue(mpsDivisionDerived.equivalentTo(mpsProductDerived));
    }

    @Test
    public void testEquivalence_ProductVsPower() {
        // Arrange
        ProductDerivedUnit cubicMetreSUT = new ProductDerivedUnit(squareMetre, metre);

        boolean isEquivalent = cubicMetre.equivalentTo(cubicMetreSUT);
        boolean isEquivalentReversed = cubicMetreSUT.equivalentTo(cubicMetre);

        assertTrue(isEquivalent);
        assertTrue(isEquivalentReversed);
    }

    @Test
    public void testEquivalence_RaisedToPowerOfOne() {
        PowerDerivedUnit metreSUT = new PowerDerivedUnit(metre, 1);

        boolean isEquivalent = metreSUT.equivalentTo(metre);
        boolean isEquivalentReversed = metre.equivalentTo(metreSUT);

        assertTrue(isEquivalent);
        assertTrue(isEquivalentReversed);
    }

    @Test
    public void testEquivalence_NamedVsDerivation() {
        // J = (kg m^2) / s^2
        assertTrue(joule.equivalentTo(joule.getDerivation()));
    }


    @Test
    public void testEquivalence_NamedVsProductDerived() {
        ProductDerivedUnit Ws = new ProductDerivedUnit(watt, second);

        assertTrue(Ws.equivalentTo(joule));
    }

    @Test
    public void testEquivalence_NamedDerivedVsRaised() {
        PowerDerivedUnit J2 = new PowerDerivedUnit(joule, 2);
        Normalization j2Norm = J2.getBaseNormalForm();
        Normalization j2BaseNorm = J2.getBaseNormalForm();

        int i = 0;
    }
}
