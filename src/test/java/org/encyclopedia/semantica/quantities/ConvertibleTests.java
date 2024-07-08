package org.encyclopedia.semantica.quantities;

import org.encyclopedia.semantica.quantities.model.derived.DivisionDerivedUnit;
import org.encyclopedia.semantica.quantities.model.Unit;
import org.junit.jupiter.api.Test;

import static org.encyclopedia.semantica.quantities.instances.SIUnits.second;
import static org.encyclopedia.semantica.quantities.instances.TemperatureUnits.celsius;
import static org.encyclopedia.semantica.quantities.instances.TemperatureUnits.fahrenheit;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConvertibleTests {

    @Test
    public void testIncompatibleConversion() {
        Unit degCPerSecond = new DivisionDerivedUnit(celsius, second);
        Unit degFPerSecond = new DivisionDerivedUnit(fahrenheit, second);

        Quantity quantity = new Quantity(12.0, degCPerSecond);

        assertThrows(IllegalArgumentException.class, () -> {
            quantity.convertTo(degFPerSecond);
        });
    }
}
