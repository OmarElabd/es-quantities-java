package org.encyclopedia.semantica.quantities.model.derived;

import org.encyclopedia.semantica.quantities.dimension.Dimension;
import org.encyclopedia.semantica.quantities.common.Normalization;
import org.encyclopedia.semantica.quantities.model.Unit;

public final class DivisionDerivedUnit extends OperatorDerivedUnit {
    public DivisionDerivedUnit(Unit numerator, Unit denominator, Dimension dimension) {
        super(numerator.getName() + " per " + denominator.getName(),
              numerator.getPlural() + " per " + denominator.getName(),
              getSymbol(numerator, denominator),
              getUnicodeSymbol(numerator, denominator),
              dimension, numerator, denominator);

        super.baseNormalForm = computeBaseNormalForm();
        super.coherentNormalForm = computeCoherentNormalForm();
    }

    public DivisionDerivedUnit(Unit numerator, Unit denominator) {
        this(numerator, denominator, Dimension.divide(numerator.getDimension(), denominator.getDimension()));
    }

    private static String getSymbol(Unit numerator, Unit denominator) {
        if (denominator instanceof OperatorDerivedUnit) {
            return numerator.getSymbol() + "/(" + denominator.getSymbol() + ")";
        } else {
            return numerator.getSymbol() + "/" + denominator.getSymbol();
        }
    }

    private static String getUnicodeSymbol(Unit leftOperand, Unit rightOperand) {
        if (rightOperand instanceof OperatorDerivedUnit) {
            return leftOperand.getUnicodeSymbol() + "/(" + rightOperand.getUnicodeSymbol() + ")";
        } else {
            return leftOperand.getUnicodeSymbol() + "/" + rightOperand.getUnicodeSymbol();
        }
    }

    @Override
    public Normalization computeCoherentNormalForm() {
        Normalization leftNormalForm = this.leftOperand.getCoherentNormalForm();
        Normalization rightNormalForm = this.rightOperand.getCoherentNormalForm();

        Normalization raisedRight = Normalization.raiseAll(rightNormalForm, -1);

        Normalization normalization = new Normalization(this, leftNormalForm, raisedRight);
        return normalization;
    }

    @Override
    public Normalization computeBaseNormalForm() {
        Normalization leftNormalForm = this.leftOperand.getBaseNormalForm();
        Normalization rightNormalForm = this.rightOperand.getBaseNormalForm();

        Normalization raisedRight = Normalization.raiseAll(rightNormalForm, -1);

        Normalization normalization = new Normalization(this, leftNormalForm, raisedRight);
        return normalization;
    }
}
