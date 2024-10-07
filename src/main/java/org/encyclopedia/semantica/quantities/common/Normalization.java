package org.encyclopedia.semantica.quantities.common;

import org.encyclopedia.semantica.quantities.dimension.Dimension;
import org.encyclopedia.semantica.quantities.instances.Dimensions;
import org.encyclopedia.semantica.quantities.model.NamedUnit;
import org.encyclopedia.semantica.quantities.model.Unit;
import org.encyclopedia.semantica.quantities.model.derived.DerivedUnit;
import org.encyclopedia.semantica.quantities.model.derived.DivisionDerivedUnit;
import org.encyclopedia.semantica.quantities.model.derived.PowerDerivedUnit;
import org.encyclopedia.semantica.quantities.model.derived.ProductDerivedUnit;

import java.util.*;
import java.util.stream.Collectors;

public class Normalization {
    private Unit unitToNormalize;
    private Dimension dimension = Dimensions.unspecified;
    private final Set<PowerDerivedUnit> productOperands;

    private Normalization(List<PowerDerivedUnit> operands) {
        this.productOperands = mergeOperands(operands);
    }

    public Normalization(PowerDerivedUnit operand) {
        this.unitToNormalize = operand;
        this.dimension = operand.getDimension();

        this.productOperands = Collections.singleton(operand);
    }

    public Normalization(DerivedUnit unitToNormalize, List<PowerDerivedUnit> operands) {
        this.unitToNormalize = unitToNormalize;
        this.dimension = unitToNormalize.getDimension();

        this.productOperands = mergeOperands(operands);
    }

    public Normalization(DerivedUnit unitToNormalize, Normalization... normalizations) {
        this.unitToNormalize = unitToNormalize;
        this.dimension = unitToNormalize.getDimension();

        List<PowerDerivedUnit> operands = new ArrayList<>();
        for (Normalization normalization : normalizations) {
            operands.addAll(normalization.productOperands);
        }

        this.productOperands = mergeOperands(operands);
    }

    public static Normalization raiseAll(Normalization normalization, int power) {
        List<PowerDerivedUnit> newOperands = new ArrayList<>();

        for (PowerDerivedUnit productOperand : normalization.getProductOperands()) {
            PowerDerivedUnit newOperand = new PowerDerivedUnit(productOperand.getOperand(), productOperand.getPower() * power);
            newOperands.add(newOperand);
        }

        return new Normalization(newOperands);
    }


    /**
     * Express the normalized form as a divison derived unit if applicable
     * e.g. [m^1, s^-2] -> m/s^s
     *
     * @return A Unit expressed as (u1^x1 * u2^x2 * ...) / (u3^x3 * u4*x4 * ...) The returning type with be
     * - NamedUnit - if all units cancel out e.g. s/s
     * - ProductDerviedUnit - if there are no divisors
     * - DivisonDerivedUnit - otherwise
     */
    public Unit toSimpleForm() {
        List<PowerDerivedUnit> numerators = this.productOperands.stream()
                                                                .filter(op -> op.getPower() > 0)
                                                                .collect(Collectors.toList());

        List<PowerDerivedUnit> denominators = this.productOperands.stream()
                                                                  .filter(op -> op.getPower() < 0)
                                                                  .collect(Collectors.toList());

        Unit result = null;

        if (productOperands.size() == 0) {
            // in the case of units cancelling (e.g. s * s^-1 => 1)
            result = new NamedUnit("", "", "1", this.dimension);
        }

        if (numerators.size() > 0) {
            Unit numerator;

            if (numerators.get(0).getPower() == 1) {
                numerator = numerators.get(0).getOperand();
            } else {
                numerator = numerators.get(0);
            }

            for (int i = 0; i < numerators.size() - 1; i++) {
                PowerDerivedUnit current = numerators.get(i + 1);

                Unit temp;
                if (current.getPower() == 1) {
                    // m^1 => m
                    temp = new ProductDerivedUnit(numerator, current.getOperand());
                } else {
                    temp = new ProductDerivedUnit(numerator, current);
                }

                numerator = temp;
            }

            // Numerator and Denominator Both Present
            if (denominators.size() > 0) {
                Unit denominator;

                PowerDerivedUnit firstNegative = denominators.get(0);
                if (denominators.get(0).getPower() == -1) {
                    denominator = firstNegative.getOperand();
                } else {
                    denominator = new PowerDerivedUnit(firstNegative.getOperand(), Math.abs(firstNegative.getPower()));
                }

                for (int i = 0; i < denominators.size() - 1; i++) {
                    PowerDerivedUnit current = denominators.get(i + 1);

                    Unit temp;
                    if (current.getPower() == -1) {
                        temp = new ProductDerivedUnit(denominator, current);
                    } else {
                        // negative sign becomes positive
                        temp = new ProductDerivedUnit(denominator, new PowerDerivedUnit(current.getOperand(), Math.abs(current.getPower())));
                    }

                    denominator = temp;
                }

                result = new DivisionDerivedUnit(numerator, denominator, this.dimension);
            } else {
                result = numerator;
            }
        }

        // No Positive Units only Negative
        if (denominators.size() > 0 && numerators.isEmpty()) {
            Unit denominator = denominators.get(0);

            for (int i = 0; i < denominators.size() - 1; i++) {
                PowerDerivedUnit current = denominators.get(i + 1);

                Unit temp;
                temp = new ProductDerivedUnit(denominator, current, this.dimension);

                denominator = temp;
            }

            result = denominator;
        }

        if (result.equals(this.unitToNormalize)) {
            return unitToNormalize;
        } else {
            return result;
        }
    }

    // TODO should return a cloned list so the internal structure isn't modified
    public Set<PowerDerivedUnit> getProductOperands() {
        return productOperands;
    }

    /**
     *
     */
    public PowerDerivedUnit getUnitWithBase(NamedUnit baseUnit) {
        for (PowerDerivedUnit productOperand : productOperands) {
            NamedUnit operand = productOperand.getOperand();
            if (operand.getBaseUnit().equals(baseUnit.getBaseUnit())) {
                return productOperand;
            }
        }

        throw new IllegalArgumentException("Could not find unit matching " + baseUnit.getBaseUnit().getName() + " in the normalization");
    }

    public PowerDerivedUnit getUnitWithSameDimension(NamedUnit baseUnit) {
        for (PowerDerivedUnit productOperand : productOperands) {
            NamedUnit operand = productOperand.getOperand();

            if (operand.getDimension().equals(baseUnit.getDimension())) {
                return productOperand;
            }
        }

        throw new IllegalArgumentException("Could not find unit with matching dimension of " + baseUnit.getDimension().getUnicodeSymbol() + " in the normalization");
    }

    @Override
    public boolean equals(Object compare) {
        if (compare instanceof Normalization) {
            Normalization normCompare = (Normalization) compare;

            if (normCompare.productOperands.size() == this.productOperands.size()) {
                return this.productOperands.containsAll(normCompare.productOperands);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Will merge product operands into a single unit
     * e.g. x^1 * x^2 = x^3
     *
     * @return a set of power units representing any possible derived or atomic/single unit
     */
    private static Set<PowerDerivedUnit> mergeOperands(List<PowerDerivedUnit> operands) {
        Map<NamedUnit, List<PowerDerivedUnit>> collectedOperands = new HashMap<>();

        List<PowerDerivedUnit> newOperands = new ArrayList<>();

        // add all to map collection such that the same units are grouped together
        // e.g. (m^2,m^5),(s,s),(w)
        for (PowerDerivedUnit productOperand : operands) {
            if (!collectedOperands.containsKey(productOperand.getOperand())) {
                collectedOperands.put(productOperand.getOperand(), new ArrayList<>());
            }

            collectedOperands.get(productOperand.getOperand()).add(productOperand);
        }

        // add powers together
        // e.g. (m^2,m^5),(s,s),(w) => m^7 s^2 w
        for (Map.Entry<NamedUnit, List<PowerDerivedUnit>> entry : collectedOperands.entrySet()) {
            if (entry.getValue().size() > 1) {
                int power = 0;

                for (PowerDerivedUnit operand : entry.getValue()) {
                    power += operand.getPower();
                }

                PowerDerivedUnit powerMergedOperands = new PowerDerivedUnit(entry.getKey(), power);
                newOperands.add(powerMergedOperands);
            } else {
                newOperands.add(entry.getValue().get(0));
            }
        }

        // drop operands with power of 0
        // e.g. (m^7, s^0, w^1) => (m^7, w^1)
        return newOperands.stream()
                          .filter(x -> x.getPower() != 0)
                          .collect(Collectors.toSet());
    }
}
