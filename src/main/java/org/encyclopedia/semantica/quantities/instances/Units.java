package org.encyclopedia.semantica.quantities.instances;

import org.encyclopedia.semantica.quantities.conversion.engine.ConversionEngine;
import org.encyclopedia.semantica.quantities.io.parser.NameParser;
import org.encyclopedia.semantica.quantities.model.Unit;
import org.encyclopedia.semantica.quantities.io.parser.SymbolParser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Units {

    // TODO PSI, KSI
    // TODO Aliases
    // TODO Cleanup
    // TODO Resolve Static Errors
    // TODO Unit System and Aliasing

    private Units() {
    }

    public static final List<Unit> All = new ArrayList<>();

    static {
        initializeUnits();

        All.addAll(SIUnits.All);
        All.addAll(SIDerivedUnits.All);
        All.addAll(NonSIUnits.All);
        All.addAll(ImperialUnits.All);
        All.addAll(USCustomaryUnits.All);
        All.addAll(ISOUnits.All);
        All.addAll(CGSUnits.All);
        All.addAll(AstronomyUnits.All);
        All.addAll(TemperatureUnits.All);
    }

    public static void initializeUnits() {
        Prefixes.initialize();
        Dimensions.initialize();
        SIUnits.initialize();
        SIDerivedUnits.initialize();
        NonSIUnits.initialize();
        ImperialUnits.initialize();
        USCustomaryUnits.initialize();
        ISOUnits.initialize();
        CGSUnits.initialize();
        AstronomyUnits.initialize();
        TemperatureUnits.initialize();
    }

    public static void initialize() {
        initializeUnits();
        ConversionEngine.initialize();
    }

    public static Unit parseSymbol(String symbol) throws ParseException {
        return SymbolParser.parseSymbol(symbol);
    }

    public static Unit parseName(String name) throws ParseException {
        return NameParser.parseName(name);
    }

    public static Optional<Unit> tryParseSymbol(String symbol) {
        return SymbolParser.tryParseSymbol(symbol);
    }

    public static Optional<Unit> tryParseName(String name) {
        return NameParser.tryParseName(name);
    }

    public static double convert(Number value, Unit from, Unit to) {
        return ConversionEngine.convert(value, from, to);
    }
}
