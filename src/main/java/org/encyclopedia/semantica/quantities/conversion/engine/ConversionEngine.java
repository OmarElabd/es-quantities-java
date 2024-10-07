package org.encyclopedia.semantica.quantities.conversion.engine;

import org.encyclopedia.semantica.quantities.common.IPrefixComparable;
import org.encyclopedia.semantica.quantities.conversion.operations.Operation;
import org.encyclopedia.semantica.quantities.conversion.operations.ReversibleOperation;
import org.encyclopedia.semantica.quantities.conversion.UnitConversion;
import org.encyclopedia.semantica.quantities.instances.Conversions;
import org.encyclopedia.semantica.quantities.model.derived.PowerDerivedUnit;
import org.encyclopedia.semantica.quantities.model.NamedUnit;
import org.encyclopedia.semantica.quantities.common.Normalization;
import org.encyclopedia.semantica.quantities.model.Unit;
import org.encyclopedia.semantica.quantities.Quantity;

// TODO use multiple graphs implemented as a Map<Dimension, Graph>
public final class ConversionEngine {
    private ConversionEngine() {}

    static {
        GraphSolver.initialize();
        Conversions.initialize();
    }

    public static void initialize() {
        // do nothing, java will invoke static constructor
    }

    public static void registerConversion(Unit fromUnit, Unit toUnit, double factor) {
        registerConversion(new UnitConversion(fromUnit, toUnit, factor));
    }

    public static void registerConversion(Unit fromUnit, Unit toUnit, Operation operation) {
        registerConversion(new UnitConversion(fromUnit, toUnit, operation));
    }

    public static void registerConversion(UnitConversion conversion) {
        Unit fromUnit = conversion.getConvertFromUnit();
        Unit toUnit = conversion.getConvertToUnit();

        Operation operation = conversion.getOperation();
        GraphSolver.addEdge(fromUnit, toUnit, operation);

        if (conversion.getOperation() instanceof ReversibleOperation) {
            // check if there is a reverse edge (do not overwrite)
            if (!GraphSolver.containsEdge(toUnit, fromUnit)) {
                ReversibleOperation inverseOperation = ((ReversibleOperation) operation).getInverseOperation();
                GraphSolver.addEdge(toUnit, fromUnit, inverseOperation);
            }
        }
    }

    public static Quantity convert(Quantity quantity, Unit convertTo) {
        double convertedValue = convert(quantity.value, quantity.unit, convertTo);
        Quantity converted = new Quantity(convertedValue, convertTo);
        return converted;
    }

    // TODO Fix
    // TODO - Cases millimeter -> feet
    public static double convert(Number value, Unit fromUnit, Unit toUnit) {
        // TODO check that they are equivalent
        // TODO check that their dimensions are equivalent

        // if they are the same unit, return the original quantity value
        if (fromUnit.equals(toUnit)) {
            return value.doubleValue();
        }

        // if they are a prefix or prefixable
        if (fromUnit instanceof IPrefixComparable && toUnit instanceof IPrefixComparable) {
            // if they have the same based, e.g. millimeter -> kilometer
            if (((NamedUnit) fromUnit).baseEquals(toUnit)) {
                return prefixConvert((IPrefixComparable) fromUnit, (IPrefixComparable) toUnit, value);
            }
        }


        // if they don't have the same base, e.g. mm to feet
        if (fromUnit instanceof IPrefixComparable || toUnit instanceof IPrefixComparable) {
            // TODO
        }

        // if there is a direct conversion registered between the two units
        if (GraphSolver.containsEdge(fromUnit, toUnit)) {
            return GraphSolver.getEdge(fromUnit, toUnit)
                              .invoke(value.doubleValue());
        }

        // if there is an indirect conversion between the two units
        if (GraphSolver.containsPath(fromUnit, toUnit))
        {
            return GraphSolver.convert(fromUnit, toUnit, value);
        }

        // get the normal forms of both units
        // TODO replace normalization call with equivalentToShallow or something else (this is just used for debugging)
        Normalization fromNormalForm = fromUnit.getCoherentNormalForm();
        Normalization toNormalForm = toUnit.getBaseNormalForm();

        if (fromNormalForm.equals(toNormalForm))
        {
            return value.doubleValue() * getFactor(fromNormalForm, toNormalForm);
        }

        Normalization baseFrom = fromUnit.getBaseNormalForm();
        Normalization baseTo = toUnit.getBaseNormalForm();

        if (fromUnit.equivalentTo(toUnit))
        {
            return value.doubleValue() * getFactor(fromUnit.getBaseNormalForm(), toUnit.getBaseNormalForm());
        }

        if (fromUnit.dimensionEquals(toUnit))
        {
            // TODO
            throw new IllegalArgumentException("Could not convert from " + fromUnit.getName() + " to " + toUnit.getName());
        }

        return value.doubleValue() * getFactor(fromUnit.getBaseNormalForm(), toUnit.getBaseNormalForm());


        // throw new IllegalArgumentException("Could not convert from " + fromUnit.getName() + " to " + toUnit.getName());
    }

    private static double getFactor(Normalization fromNorm, Normalization toNorm) {
        int scale = 0;
        double factor = 1;

        for (PowerDerivedUnit fromOperand : fromNorm.getProductOperands()) {
            NamedUnit baseForm = fromOperand.getOperand();
            PowerDerivedUnit toOperand = toNorm.getUnitWithSameDimension(baseForm.getBaseUnit());

            int localScale = 0;
            double localFactor = 1;
            if (fromOperand.getOperand() instanceof IPrefixComparable && toOperand.getOperand() instanceof IPrefixComparable) {
                int fromScale = ((IPrefixComparable) fromOperand.getOperand()).getPrefixScale();
                int toScale = ((IPrefixComparable) toOperand.getOperand()).getPrefixScale();

                localScale = fromScale - toScale;
            } else {
                localFactor = GraphSolver.getConversionFactor(fromOperand.getOperand(), toOperand.getOperand());
            }

            localScale = localScale * fromOperand.getPower();
            localFactor = Math.pow(localFactor, fromOperand.getPower());
            scale = scale + localScale;
            factor = factor * localFactor;
        }

        return factor * Math.pow(10, scale);
    }

    private static double prefixConvert(IPrefixComparable fromUnit, IPrefixComparable toUnit, Number value) {
        int fromPrefixBase = fromUnit.getPrefixBase();
        int fromPrefixScale = fromUnit.getPrefixScale();
        int toPrefixBase = toUnit.getPrefixBase();
        int toPrefixScale = toUnit.getPrefixScale();

        if (fromPrefixScale == 0 || toPrefixScale == 0) {
            // since at least one unit will have a prefix scale of 0, this should cancel
            int scale = fromPrefixScale - toPrefixScale;
            // since at least one unit will have a prefix base of 1, this should cancel
            int base = fromPrefixBase * toPrefixBase;

            return value.doubleValue() * Math.pow(base, scale);
        } else if (fromPrefixBase == toPrefixBase) {
            int scale = fromPrefixScale - toPrefixScale;

            return value.doubleValue() * Math.pow(fromPrefixBase, scale);
        } else {
            return value.doubleValue() * (Math.pow(fromPrefixBase, fromPrefixScale) / Math.pow(toPrefixBase, toPrefixScale));
        }
    }
}
