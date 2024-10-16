package org.encyclopedia.semantica.quantities.model.derived;

import org.encyclopedia.semantica.quantities.dimension.Dimension;
import org.encyclopedia.semantica.quantities.model.NamedUnit;
import org.encyclopedia.semantica.quantities.model.Unit;

public final class ReciprocalDerivedUnit extends PowerDerivedUnit {
    public ReciprocalDerivedUnit(NamedUnit operand) {
        super(operand, -1,
              "reciprocal " + operand.getName(),
              "reciprocal " + operand.getPlural(),
              computeSymbol(operand),
              computeUnicodeSymbol(operand));

        super.dimension = Dimension.reciprocal(operand.getDimension());
        super.unitSystem = operand.getUnitSystem();
    }

    public ReciprocalDerivedUnit(NamedUnit operand, Dimension dimension) {
        super(operand, -1,
              "reciprocal " + operand.getName(),
              "reciprocal " + operand.getPlural(),
              computeSymbol(operand),
              computeUnicodeSymbol(operand));

        super.dimension = dimension;
        super.unitSystem = operand.getUnitSystem();
    }

    private static String computeSymbol(Unit operand) {
        if (operand instanceof DerivedUnit) {
            return "(" + operand.getSymbol() + ")^-1";
        } else {
            return operand.getSymbol() + "^-1";
        }
    }

    private static String computeUnicodeSymbol(Unit operand) {
        if (operand instanceof DerivedUnit) {
            return "(" + operand.getUnicodeSymbol() + ")" + "\u207B\u00B9";
        } else {
            return operand.getUnicodeSymbol() + "\u207B\u00B9";
        }
    }
}
