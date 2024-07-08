package org.encyclopedia.semantica.quantities.io.parser;

import org.encyclopedia.semantica.quantities.Prefix;
import org.encyclopedia.semantica.quantities.instances.Prefixes;
import org.encyclopedia.semantica.quantities.instances.Units;
import org.encyclopedia.semantica.quantities.model.NamedUnit;
import org.encyclopedia.semantica.quantities.model.PrefixableUnit;
import org.encyclopedia.semantica.quantities.model.PrefixedUnit;
import org.encyclopedia.semantica.quantities.model.Unit;
import org.encyclopedia.semantica.quantities.model.derived.DivisionDerivedUnit;
import org.encyclopedia.semantica.quantities.model.derived.PowerDerivedUnit;
import org.encyclopedia.semantica.quantities.model.derived.ProductDerivedUnit;
import org.encyclopedia.semantica.quantities.utils.StringRepresentationUtil;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NameParser {

    public static Unit parseName(String name) throws ParseException {
        Optional<Unit> result = tryParseName(name);

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ParseException("Could not parse unit from name: " + name, 0);
        }
    }

    public static Optional<Unit> tryParseName(String name) {
        List<Unit> allUnits = Units.All;

        // check if the unit symbol already exists in the existing units
        List<Unit> initialMatches = allUnits.stream()
                                            .filter(unit -> unit.getName().equals(name) || unit.getUnicodeName().equals(name))
                                            .collect(Collectors.toList());

        if (initialMatches.size() > 0) {
            return Optional.of(initialMatches.get(0));
        }

        if (name.startsWith("reciprocal")) {
           Optional<Unit> result = parseReciprocalUnit(name);
           return result;
        }

        int divisionCount = StringUtils.countMatches(name, " per ");
        if (divisionCount > 1) {
            // TODO can have multiple "per"
            return Optional.empty();
        }

        // Parse Division Derived Unit
        if (divisionCount == 1) {
            String[] splitStr = StringUtils.splitByWholeSeparator(name, " per ", 2);
            String leftSide = splitStr[0];
            String rightSide = splitStr[1];

            Optional<Unit> result = parseDivisionUnit(leftSide, rightSide);

            return result;
        }

        if (name.contains(" ")) {
            int index = 0;
            int productIndex = name.indexOf(" ");
            String left = StringUtils.substring(name, 0, productIndex);
            if (StringRepresentationUtil.PowerNames.contains(left)) {
                index = name.indexOf(" ", productIndex + 1);
            } else {
                index = productIndex;
            }

            if (index == -1) {
                // Parse Power Derived Unit
                String[] split = StringUtils.split(name, " ", 2);
                String powerName = split[0];
                String unitName = split[1];

                Optional<Unit> result = parsePowerUnit(unitName, powerName);

                return result;
            } else {
                // Parse Product Derived Unit
                String leftSide = name.substring(0, index);
                String rightSide = name.substring(index + 1);

                Optional<Unit> result = parseProductUnit(leftSide, rightSide);

                return result;
            }
        }

        // Parse Prefixed Unit
        Optional<Prefix> prefixMatch = Prefixes.All.stream()
                                                   .filter(p -> name.startsWith(p.getName()))
                                                   .findFirst();
        if (prefixMatch.isPresent()) {
            Prefix prefix = prefixMatch.get();
            int index = name.lastIndexOf(prefix.getName()) + prefix.getName().length();
            String unitName = name.substring(index);
            Optional<Unit> unitResult = tryParseName(unitName);

            if (unitResult.isPresent() && unitResult.get() instanceof PrefixableUnit) {
                PrefixedUnit prefixedUnit = new PrefixedUnit(prefix, (PrefixableUnit) unitResult.get());

                return Optional.of(prefixedUnit);
            }
        }

        return Optional.empty();
    }

    private static Optional<Unit> parseReciprocalUnit(String name) {
        // Parse Product Derived Unit
        return Optional.empty();
    }

    private static Optional<Unit> parseDivisionUnit(String leftSide, String rightSide) {
        Optional<Unit> leftUnit = tryParseName(leftSide);
        Optional<Unit> rightUnit = tryParseName(rightSide);

        if (leftUnit.isPresent() && rightUnit.isPresent()) {
            DivisionDerivedUnit unit = new DivisionDerivedUnit(leftUnit.get(), rightUnit.get());

            return Optional.of(unit);
        } else {
            return Optional.empty();
        }
    }

    private static Optional<Unit> parseProductUnit(String leftSide, String rightSide) {
        Optional<Unit> leftUnit = tryParseName(leftSide);
        Optional<Unit> rightUnit = tryParseName(rightSide);

        if (leftUnit.isPresent() && rightUnit.isPresent()) {
            ProductDerivedUnit unit = new ProductDerivedUnit(leftUnit.get(), rightUnit.get());

            return Optional.of(unit);
        } else {
            return Optional.empty();
        }
    }

    private static Optional<Unit> parsePowerUnit(String unitName, String powerName) {
        int power = StringRepresentationUtil.getPowerNameValue(powerName);
        if (power != -1) {
            Optional<Unit> unitResult = tryParseName(unitName);

            if (unitResult.isPresent() && unitResult.get() instanceof NamedUnit) {
                Unit powerUnit = new PowerDerivedUnit((NamedUnit) unitResult.get(), power);

                return Optional.of(powerUnit);
            }
        }

        return Optional.empty();
    }
}
