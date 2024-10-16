package org.encyclopedia.semantica.quantities.instances;

import org.encyclopedia.semantica.quantities.model.PrefixableUnit;
import org.encyclopedia.semantica.quantities.model.PrefixedUnit;
import org.encyclopedia.semantica.quantities.model.Unit;
import org.encyclopedia.semantica.quantities.UnitSystem;

import java.util.Arrays;
import java.util.List;

public final class SIUnits {
    private SIUnits() {}

    public static void initialize() {}

    public static final PrefixableUnit metre = new PrefixableUnit("metre", "metres", "m",
                                                                  Dimensions.length, UnitSystem.SI);

    public static final PrefixedUnit centimetre = new PrefixedUnit(Prefixes.centi, metre);

    public static final PrefixableUnit gram = new PrefixableUnit("gram", "grams", "g",
                                                                 Dimensions.mass, UnitSystem.SI);

    public static final PrefixedUnit kilogram = new PrefixedUnit(Prefixes.kilo, gram);

    public static final PrefixableUnit second = new PrefixableUnit("second", "seconds", "s",
                                                                   Dimensions.time, UnitSystem.SI);

    public static final PrefixableUnit ampere = new PrefixableUnit("ampere", "amperes", "A",
                                                                   Dimensions.electricCurrent, UnitSystem.SI);

    public static final PrefixableUnit kelvin = new PrefixableUnit("kelvin", "kelvin", "K",
                                                                   Dimensions.temperature, UnitSystem.SI);

    public static final PrefixableUnit mole = new PrefixableUnit("mole", "moles", "mol",
                                                                 Dimensions.amountOfSubstance, UnitSystem.SI);

    public static final PrefixableUnit candela = new PrefixableUnit("candela", "candela", "cd",
                                                                    Dimensions.luminousIntensity, UnitSystem.SI);

    // Aliases
    public static final PrefixableUnit meter = metre;

    public static final List<Unit> All = Arrays.asList(metre, kilogram, second, ampere, kelvin, mole, candela);
}
