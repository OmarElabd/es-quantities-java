package org.encyclopedia.semantica.quantities.model;

// TODO Revise - Incomplete
public class AliasedUnit extends NamedUnit {
    Unit aliasedFrom;

    public AliasedUnit(String name, String plural, String symbol) {
        super(name, plural, symbol);
    }
}
