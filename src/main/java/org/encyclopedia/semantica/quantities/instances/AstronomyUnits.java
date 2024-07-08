package org.encyclopedia.semantica.quantities.instances;

import org.encyclopedia.semantica.quantities.model.NamedUnit;
import org.encyclopedia.semantica.quantities.model.PrefixableUnit;
import org.encyclopedia.semantica.quantities.model.Unit;

import java.util.Arrays;
import java.util.List;

public final class AstronomyUnits {
    public static void initialize() {}

    public static final NamedUnit parsec = new PrefixableUnit("parsec", "parsecs", "pc", "pc", Dimensions.length,
                                                              Arrays.asList(Prefixes.giga, Prefixes.mega));

    public static final NamedUnit solarMass = new PrefixableUnit("solar mass", "solar masses", "Mo", "Mo", Dimensions.mass);

    public static final List<Unit> All = Arrays.asList(parsec, solarMass);

}
