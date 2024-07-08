package org.encyclopedia.semantica.quantities.model.derived;

import org.encyclopedia.semantica.quantities.dimension.Dimension;
import org.encyclopedia.semantica.quantities.model.Unit;
import org.encyclopedia.semantica.quantities.UnitSystem;

import java.util.Objects;

public abstract class OperatorDerivedUnit extends DerivedUnit {
    public Unit leftOperand;
    public Unit rightOperand;

    protected OperatorDerivedUnit(String name, String plural, String symbol, String unicodeSymbol,
                                  Unit leftOperand, Unit rightOperand) {
        super(name, plural, symbol, unicodeSymbol);

        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;

        if (leftOperand.getUnitSystem().equals(rightOperand.getUnitSystem())) {
            this.unitSystem = leftOperand.getUnitSystem();
        } else {
            this.unitSystem = UnitSystem.Unspecified;
        }
    }

    protected OperatorDerivedUnit(String name, String plural, String symbol, String unicodeSymbol, Dimension dimension,
                                  Unit leftOperand, Unit rightOperand) {
        super(name, plural, symbol, unicodeSymbol, dimension);
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;

        if (leftOperand.getUnitSystem().equals(rightOperand.getUnitSystem())) {
            this.unitSystem = leftOperand.getUnitSystem();
        } else {
            this.unitSystem = UnitSystem.Unspecified;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OperatorDerivedUnit)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }

        OperatorDerivedUnit unit = (OperatorDerivedUnit) obj;

        return Objects.equals(leftOperand, unit.leftOperand) &&
                Objects.equals(rightOperand, unit.rightOperand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), leftOperand, rightOperand);
    }

    //region Getters
    @Override
    public String getName() {
        if (this.equals(this.getSimplifiedForm())) {
            return name;
        } else {
            return this.getSimplifiedForm().getName();
        }
    }

    @Override
    public String getUnicodeName() {
        if (this.equals(this.getSimplifiedForm())) {
            return unicodeName;
        } else {
            return this.getSimplifiedForm().getUnicodeName();
        }
    }

    @Override
    public String getPlural() {
        if (this.equals(this.getSimplifiedForm())) {
            return plural;
        } else {
            return this.getSimplifiedForm().getPlural();
        }
    }

    @Override
    public String getUnicodePlural() {
        if (this.equals(this.getSimplifiedForm())) {
            return unicodePlural;
        } else {
            return this.getSimplifiedForm().getUnicodePlural();
        }
    }

    @Override
    public String getSymbol() {
        if (this.equals(this.getSimplifiedForm())) {
            return symbol;
        } else {
            return this.getSimplifiedForm().getSymbol();
        }
    }

    @Override
    public String getUnicodeSymbol() {
        if (this.equals(this.getSimplifiedForm())) {
            return unicodeSymbol;
        } else {
            return this.getSimplifiedForm().getUnicodeSymbol();
        }
    }
    //endregion
}
