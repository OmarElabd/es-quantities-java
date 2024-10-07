package org.encyclopedia.semantica.quantities.model.derived;

import org.encyclopedia.semantica.quantities.common.IDerivableUnit;
import org.encyclopedia.semantica.quantities.dimension.Dimension;
import org.encyclopedia.semantica.quantities.model.Unit;

/**
 * The base class for different derived (or composite) units. Derived units contain multiple base units.
 */
public abstract class DerivedUnit extends Unit implements IDerivableUnit {
    public DerivedUnit(String name, String plural, String symbol) {
        super(name, plural, symbol);
    }

    public DerivedUnit(String name, String plural, String symbol, Dimension dimension) {
        super(name, plural, symbol, dimension);
    }

    public DerivedUnit(String name, String plural, Dimension dimension) {
        super(name, plural, dimension);
    }

    public DerivedUnit(String name, String plural, String symbol, String unicodeSymbol) {
        super(name, plural, symbol, unicodeSymbol);
    }

    public DerivedUnit(String name, String plural, String symbol, String unicodeSymbol, Dimension dimension) {
        super(name, plural, symbol, unicodeSymbol, dimension);
    }

    @Override
    public Unit getSimplifiedForm() {
        return this.getCoherentNormalForm().toSimpleForm();
    }

    public DerivedUnit getDerivation() {
        return this;
    }
}
