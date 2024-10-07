package org.encyclopedia.semantica.quantities.model;

import org.encyclopedia.semantica.quantities.common.IPrefixComparable;
import org.encyclopedia.semantica.quantities.Prefix;

import java.util.Objects;

/**
 * Represents a specific unit that has been prefixed, e.g. millimeter
 */
public class PrefixedUnit extends NamedUnit implements IPrefixComparable {
    public Prefix prefix;
    public final PrefixableUnit baseUnit;

    public PrefixedUnit(Prefix prefix, PrefixableUnit unit) {
        super(prefix.getName() + unit.name,
              prefix.getName() + unit.plural,
              prefix.getSymbol() + unit.symbol,
              prefix.getUnicodeSymbol() + unit.unicodeSymbol,
              unit.dimension);

        this.prefix = prefix;
        this.baseUnit = unit;
        this.unitSystem = baseUnit.unitSystem;

        if (!unit.applicablePrefixes.contains(prefix)) {
            throw new IllegalArgumentException(
                    "org.encyclopedia.semantica.quantities.SIPrefix " + prefix.getName() + " is not an applicable prefix to the unit " + unit.name);
        }
    }

    public PrefixedUnit(Prefix prefix, PrefixableUnit unit, String name, String plural, String symbol, String unicodeSymbol) {
        super(name, plural, symbol, unicodeSymbol, unit.dimension);

        this.prefix = prefix;
        this.baseUnit = unit;
        this.unitSystem = baseUnit.unitSystem;

        if (!unit.applicablePrefixes.contains(prefix)) {
            throw new IllegalArgumentException(
                    "org.encyclopedia.semantica.quantities.SIPrefix " + prefix.getName() + " is not an applicable prefix to the unit " + unit.name);
        }
    }

    public Prefix getPrefix() {
        return prefix;
    }

    @Override
    public int getPrefixScale() {
        return prefix.getScale();
    }

    @Override
    public int getPrefixBase() {
        return prefix.getBase();
    }

    @Override
    public PrefixableUnit getBaseUnit() {
        return baseUnit;
    }

    // TODO add setters for changing prefix

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PrefixedUnit)) {
            return false;
        }

        if (!super.equals(obj)) {
            return false;
        }

        PrefixedUnit unit = (PrefixedUnit) obj;

        return Objects.equals(prefix, unit.prefix) &&
                Objects.equals(baseUnit, unit.baseUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), prefix, baseUnit);
    }
}
