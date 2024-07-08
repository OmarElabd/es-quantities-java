package org.encyclopedia.semantica.quantities.instances;

import org.encyclopedia.semantica.quantities.model.derived.DerivedUnit;
import org.encyclopedia.semantica.quantities.model.derived.DivisionDerivedUnit;
import org.encyclopedia.semantica.quantities.model.derived.ProductDerivedUnit;
import org.encyclopedia.semantica.quantities.model.CoherentDerivedUnit;
import org.encyclopedia.semantica.quantities.model.NamedUnit;
import org.encyclopedia.semantica.quantities.model.Unit;
import org.encyclopedia.semantica.quantities.UnitSystem;

import java.util.Arrays;
import java.util.List;

public final class CGSUnits {
    private CGSUnits() {
    }

    public static void initialize() {
    }

    // Aliases
    public static final NamedUnit centimetre = SIUnits.centimetre;
    public static final NamedUnit gram = SIUnits.gram;
    public static final NamedUnit second = SIUnits.second;

    // Derived
    public static final DerivedUnit centimetrePerSecond = new DivisionDerivedUnit(centimetre, second);
    public static final DerivedUnit centimetrePerSecondSquared = new DivisionDerivedUnit(centimetrePerSecond, second);

    // Named Derived
    public static final CoherentDerivedUnit dyne = new CoherentDerivedUnit("dyne", "dynes", "dyn", "dyn",
                                                                           Dimensions.force, UnitSystem.CentimetreGramSecond, Prefixes.None,
                                                                           new ProductDerivedUnit(gram, centimetrePerSecondSquared));

    public static final CoherentDerivedUnit erg = new CoherentDerivedUnit("erg", "ergs", "erg", "erg",
                                                                          Dimensions.energy, UnitSystem.CentimetreGramSecond, Prefixes.None,
                                                                          new ProductDerivedUnit(dyne, centimetre));

    public static final NamedUnit poise = new NamedUnit("poise", "poises", "P", "P",
                                                        Dimensions.dynamicViscosity, UnitSystem.CentimetreGramSecond);

    public static final NamedUnit stokes = new NamedUnit("strokes", "strokes", "St", "St",
                                                         Dimensions.kinematicViscosity, UnitSystem.CentimetreGramSecond);

    public static final NamedUnit stilb = new NamedUnit("stilb", "stilbs", "sb", "sb", Dimensions.luminance,
                                                        UnitSystem.CentimetreGramSecond);

    public static final NamedUnit phot = new NamedUnit("phot", "phots", "ph", "ph", Dimensions.illuminance,
                                                       UnitSystem.CentimetreGramSecond);

    public static final NamedUnit gal = new NamedUnit("gal", "gals", "Gal", "Gal", Dimensions.acceleration,
                                                      UnitSystem.CentimetreGramSecond);

    public static final NamedUnit maxwell = new NamedUnit("maxwell", "maxwells", "Mx", "Mx",
                                                          Dimensions.magneticFlux, UnitSystem.CentimetreGramSecond);

    public static final NamedUnit gauss = new NamedUnit("gauss", "gauss", "G", "G",
                                                        Dimensions.magneticFluxDensity, UnitSystem.CentimetreGramSecond);

    public static final NamedUnit oersted = new NamedUnit("oersted", "\u0153rsted", "oersted", "\u0153rsted", "Oe", "Oe",
                                                          Dimensions.magneticFieldStrength, UnitSystem.CentimetreGramSecond);

    public static final NamedUnit kayser = new NamedUnit("kayser", "kayers", "K", "K", Dimensions.waveNumber,
                                                         UnitSystem.CentimetreGramSecond);

    // Electromagnetic
    // Abampere
    public static final NamedUnit abampere = new NamedUnit("abampere", "abamperes", "abA", Dimensions.electricCurrent,
                                                           UnitSystem.CentimetreGramSecond);

    //    public static final DivisionDerivedUnit abcoulomb = new DivisionDerivedUnit("abcoulomb", "abcoulombs", "abA", Dimensions.electricCurrent,
    //                                                                        UnitSystem.CentimetreGramSecond);


    public static final List<Unit> All = Arrays.asList(centimetre, gram, second, /*erg, dyne,*/ poise, stokes, stilb, phot, gal, maxwell,
                                                       gauss, oersted, kayser);
}
