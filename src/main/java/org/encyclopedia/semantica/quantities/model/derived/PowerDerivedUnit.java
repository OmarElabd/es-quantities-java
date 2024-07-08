package org.encyclopedia.semantica.quantities.model.derived;

import org.encyclopedia.semantica.quantities.dimension.Dimension;
import org.encyclopedia.semantica.quantities.model.NamedUnit;
import org.encyclopedia.semantica.quantities.common.Normalization;
import org.encyclopedia.semantica.quantities.model.Unit;
import org.encyclopedia.semantica.quantities.utils.StringRepresentationUtil;

import java.util.Objects;

public class PowerDerivedUnit extends DerivedUnit {
    protected NamedUnit operand;
    protected int power;

    protected PowerDerivedUnit(NamedUnit operand, int power, String name, String plural, String symbol, String unicodeSymbol) {
        super(name, plural, symbol, unicodeSymbol);
        this.operand = operand;
        this.power = power;

        super.baseNormalForm = computeBaseNormalForm();
        super.coherentNormalForm = computeCoherentNormalForm();
    }

    public PowerDerivedUnit(NamedUnit operand, int power) {
        super(StringRepresentationUtil.getRaisedPowerName(operand.getName(), power),
              StringRepresentationUtil.getRaisedPowerPlural(operand.getPlural(), power),
              computeSymbol(operand, power),
              computeUnicodeSymbol(operand, power));

        this.operand = operand;
        this.power = power;
        this.unitSystem = operand.getUnitSystem();

        super.dimension = Dimension.power(operand.getDimension(), power);

        super.baseNormalForm = computeBaseNormalForm();
        super.coherentNormalForm = computeCoherentNormalForm();
    }

    public PowerDerivedUnit(NamedUnit operand, int power, Dimension dimension) {
        super(StringRepresentationUtil.getRaisedPowerName(operand.getName(), power),
              StringRepresentationUtil.getRaisedPowerPlural(operand.getPlural(), power),
              computeSymbol(operand, power),
              computeUnicodeSymbol(operand, power),
              dimension);

        this.operand = operand;
        this.power = power;
        this.unitSystem = operand.getUnitSystem();

        super.baseNormalForm = computeBaseNormalForm();
        super.coherentNormalForm = computeCoherentNormalForm();
    }

    private static String computeSymbol(NamedUnit operand, int power) {
        // x^1 => x
        if (power == 1) {
            return operand.getSymbol();
        } else {
            return operand.getSymbol() + "^" + Integer.toString(power);
        }
    }

    private static String computeUnicodeSymbol(NamedUnit operand, int power) {
        // x^1 => x
        if (power == 1) {
            return operand.getSymbol();
        } else {
            return operand.getUnicodeSymbol() + StringRepresentationUtil.toUnicodePowerString(power);
        }
    }

    @Override
    public Normalization computeCoherentNormalForm() {
        Normalization normalForm = new Normalization(this);
        return normalForm;
    }

    @Override
    public Normalization computeBaseNormalForm() {
        Normalization normalForm = new Normalization(this);
        return normalForm;
    }

    public int getPower() {
        return power;
    }

    public NamedUnit getOperand() {
        return operand;
    }

    @Override
    public Unit getSimplifiedForm() {
        return this.getCoherentNormalForm().toSimpleForm();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PowerDerivedUnit)) {
            return false;
        }

        if (!super.equals(obj)) {
            return false;
        }

        PowerDerivedUnit unit = (PowerDerivedUnit) obj;
        return power == unit.power &&
                Objects.equals(operand, unit.operand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), operand, power);
    }
}
