package org.encyclopedia.semantica.quantities;

import org.encyclopedia.semantica.quantities.conversion.engine.ConversionEngine;
import org.encyclopedia.semantica.quantities.model.Unit;
import org.encyclopedia.semantica.quantities.model.derived.DivisionDerivedUnit;
import org.encyclopedia.semantica.quantities.model.derived.ProductDerivedUnit;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Quantity implements Comparable<Quantity> {
    public Number value;
    public Unit unit;

    public Quantity(Number value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public static Quantity of(Number value, Unit unit) {
        return new Quantity(value, unit);
    }

    public Number getValue() { return this.value;}

    public int intValue() { return this.value.intValue(); }

    public double doubleValue() { return this.value.doubleValue(); }

    @Override
    public String toString() {
        return this.value.toString() + " " + this.unit.getPlural();
    }

    public String toUnicodeString() {
        return this.value.toString() + " " + this.unit.getUnicodePlural();
    }

    public Quantity convertTo(Unit convert) {
        return ConversionEngine.convert(this, convert);
    }

    //    public static Quantity parse(String str) {
    //
    //    }

    // TODO
    @Override
    public int compareTo(Quantity comparison) {
        // if (!this.unit.equals(comparison.unit)) {
        //     throw new IllegalArgumentException("Cannot compare quantities with different units");
        // }


        /*if(this.unit.equals(compare.unit)) {
            return Number.compare(this.value, compare.value);
        } else if (this.unit.equivalentTo(compare.unit)) {

        } else if (this.unit.dimension.equivalentTo(compare.unit.dimension)) {

        } else {

        }

        // Throw exception if they are not comparables

        // if not unknown
        // if the dimensions are equal
        // if the units are the same
        // if the units are equivalent
        // if the units are not equal/equivalent then convertTo one to the other then compare

        */
        return Double.compare(this.doubleValue(), comparison.doubleValue());
    }


    public int compareTo(Number comparison) {
        return Double.compare(this.doubleValue(), comparison.doubleValue());
    }

    public Quantity multiply(Quantity multiplier) {
        return Quantity.of(multiply(this.value, multiplier.value), new ProductDerivedUnit(this.unit, multiplier.unit));
    }

    public Quantity add(Quantity addend) {
        // TODO check if this.unit == addend.unit
        // TODO convert if needed
        return Quantity.of(add(this.value, addend.value), this.unit);
    }

    public Quantity subtract(Quantity subtrahend) {
        // TODO check if this.unit == subtrahend.unit
        return Quantity.of(subtract(this.value, subtrahend.value), this.unit);
    }

    public Quantity divide(Quantity divisor) {
        return Quantity.of(divide(this.value, divisor.value), new DivisionDerivedUnit(this.unit, divisor.unit));
    }

    public void add(Number addend) {
        this.value = add(this.value, addend);
    }

    public void subtract(Number subtrahend) {
        this.value = subtract(this.value, subtrahend);
    }

    public void multiply(Number multiplier) {
        this.value = multiply(this.value, multiplier);
    }

    public void divide(Number divisor) {
        this.value = divide(this.value, divisor);
    }

    private static Number add(Number augend, Number addend) {
        if (augend == null || addend == null) {
            throw new IllegalArgumentException("Both numbers must be non-null");
        }

        if (isBigDecimal(augend) || isBigDecimal(addend)) {
            return toBigDecimal(augend).add(toBigDecimal(addend));
        } else if (isBigInteger(augend) || isBigInteger(addend)) {
            return toBigInteger(augend).add(toBigInteger(addend));
        } else if (isDouble(augend) || isDouble(addend)) {
            return augend.doubleValue() + addend.doubleValue();
        } else if (isFloat(augend) || isFloat(addend)) {
            return augend.floatValue() + addend.floatValue();
        } else if (isLong(augend) || isLong(addend)) {
            return augend.longValue() + addend.longValue();
        } else {
            return augend.intValue() + addend.intValue();
        }
    }

    private static Number subtract(Number minuend, Number subtrahend) {
        if (minuend == null || subtrahend == null) {
            throw new IllegalArgumentException("Both numbers must be non-null");
        }

        if (isBigDecimal(minuend) || isBigDecimal(subtrahend)) {
            return toBigDecimal(minuend).subtract(toBigDecimal(subtrahend));
        } else if (isBigInteger(minuend) || isBigInteger(subtrahend)) {
            return toBigInteger(minuend).subtract(toBigInteger(subtrahend));
        } else if (isDouble(minuend) || isDouble(subtrahend)) {
            return minuend.doubleValue() - subtrahend.doubleValue();
        } else if (isFloat(minuend) || isFloat(subtrahend)) {
            return minuend.floatValue() - subtrahend.floatValue();
        } else if (isLong(minuend) || isLong(subtrahend)) {
            return minuend.longValue() - subtrahend.longValue();
        } else {
            return minuend.intValue() - subtrahend.intValue();
        }
    }

    private static Number multiply(Number multiplicand, Number multiplier) {
        if (multiplicand == null || multiplier == null) {
            throw new IllegalArgumentException("Both numbers must be non-null");
        }

        if (isBigDecimal(multiplicand) || isBigDecimal(multiplier)) {
            return toBigDecimal(multiplicand).multiply(toBigDecimal(multiplier));
        } else if (isBigInteger(multiplicand) || isBigInteger(multiplier)) {
            return toBigInteger(multiplicand).multiply(toBigInteger(multiplier));
        } else if (isDouble(multiplicand) || isDouble(multiplier)) {
            return multiplicand.doubleValue() * multiplier.doubleValue();
        } else if (isFloat(multiplicand) || isFloat(multiplier)) {
            return multiplicand.floatValue() * multiplier.floatValue();
        } else if (isLong(multiplicand) || isLong(multiplier)) {
            return multiplicand.longValue() * multiplier.longValue();
        } else {
            return multiplicand.intValue() * multiplier.intValue();
        }
    }

    private static Number divide(Number dividend, Number divisor) {
        if (dividend == null || divisor == null) {
            throw new IllegalArgumentException("Both numbers must be non-null");
        }

        if (isBigDecimal(dividend) || isBigDecimal(divisor)) {
            return toBigDecimal(dividend).divide(toBigDecimal(divisor));
        } else if (isBigInteger(dividend) || isBigInteger(divisor)) {
            return new BigDecimal(toBigInteger(dividend)).divide(new BigDecimal(toBigInteger(divisor)));
        } else if (isDouble(dividend) || isDouble(divisor)) {
            return dividend.doubleValue() / divisor.doubleValue();
        } else if (isFloat(dividend) || isFloat(divisor)) {
            return dividend.floatValue() / divisor.floatValue();
        } else if (isLong(dividend) || isLong(divisor)) {
            return dividend.longValue() / divisor.longValue();
        } else {
            return dividend.intValue() / divisor.intValue();
        }
    }

    private static boolean isDouble(Number number) {
        return number instanceof Double;
    }

    private static boolean isFloat(Number number) {
        return number instanceof Float;
    }

    private static boolean isLong(Number number) {
        return number instanceof Long;
    }

    private static boolean isBigDecimal(Number number) {
        return number instanceof BigDecimal;
    }

    private static boolean isBigInteger(Number number) {
        return number instanceof BigInteger;
    }

    private static BigDecimal toBigDecimal(Number number) {
        if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        } else if (number instanceof BigInteger) {
            return new BigDecimal((BigInteger) number);
        } else {
            return new BigDecimal(number.toString());
        }
    }

    private static BigInteger toBigInteger(Number number) {
        if (number instanceof BigInteger) {
            return (BigInteger) number;
        } else {
            return new BigInteger(number.toString());
        }
    }
}
