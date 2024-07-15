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

    public Number getValue() {return this.value;}

    public int intValue() {return this.value.intValue();}

    public double doubleValue() {return this.value.doubleValue();}

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
            throw new IllegalArgumentException("Both addends must be non-null");
        }

        if (augend instanceof BigDecimal || addend instanceof BigDecimal) {
            return toBigDecimal(augend).add(toBigDecimal(addend));
        } else if (augend instanceof BigInteger || addend instanceof BigInteger) {
            if (augend instanceof Double || addend instanceof Double || augend instanceof Float || addend instanceof Float) {
                return toBigDecimal(augend).add(toBigDecimal(addend));
            } else {
                return toBigInteger(augend).add(toBigInteger(addend));
            }
        } else if (augend instanceof Double || addend instanceof Double) {
            return augend.doubleValue() + addend.doubleValue();
        } else if (augend instanceof Float || addend instanceof Float) {
            return augend.floatValue() + addend.floatValue();
        } else if (augend instanceof Long || addend instanceof Long) {
            return augend.longValue() + addend.longValue();
        } else {
            // int, short, and byte are promoted to int in arithmetic operations
            return augend.intValue() + addend.intValue();
        }
    }

    private static Number subtract(Number minuend, Number subtrahend) {
        if (minuend == null || subtrahend == null) {
            throw new IllegalArgumentException("Both minuend and subtrahend must be non-null");
        }

        if (minuend instanceof BigDecimal || subtrahend instanceof BigDecimal) {
            return toBigDecimal(minuend).subtract(toBigDecimal(subtrahend));
        } else if (minuend instanceof BigInteger || subtrahend instanceof BigInteger) {
            if (minuend instanceof Double || subtrahend instanceof Double || minuend instanceof Float || subtrahend instanceof Float) {
                return toBigDecimal(minuend).subtract(toBigDecimal(subtrahend));
            } else {
                return toBigInteger(minuend).subtract(toBigInteger(subtrahend));
            }
        } else if (minuend instanceof Double || subtrahend instanceof Double) {
            return minuend.doubleValue() - subtrahend.doubleValue();
        } else if (minuend instanceof Float || subtrahend instanceof Float) {
            return minuend.floatValue() - subtrahend.floatValue();
        } else if (minuend instanceof Long || subtrahend instanceof Long) {
            return minuend.longValue() - subtrahend.longValue();
        } else {
            // int, short, and byte are promoted to int in arithmetic operations
            return minuend.intValue() - subtrahend.intValue();
        }
    }


    private static Number multiply(Number multiplicand, Number multiplier) {
        if (multiplicand == null || multiplier == null) {
            throw new IllegalArgumentException("Both multiplicand and multiplier must be non-null");
        }

        if (multiplicand instanceof BigDecimal || multiplier instanceof BigDecimal) {
            return toBigDecimal(multiplicand).multiply(toBigDecimal(multiplier));
        } else if (multiplicand instanceof BigInteger || multiplier instanceof BigInteger) {
            if (multiplicand instanceof Double || multiplier instanceof Double || multiplicand instanceof Float || multiplier instanceof Float) {
                return toBigDecimal(multiplicand).multiply(toBigDecimal(multiplier));
            } else {
                return toBigInteger(multiplicand).multiply(toBigInteger(multiplier));
            }
        } else if (multiplicand instanceof Double || multiplier instanceof Double) {
            return multiplicand.doubleValue() * multiplier.doubleValue();
        } else if (multiplicand instanceof Float || multiplier instanceof Float) {
            return multiplicand.floatValue() * multiplier.floatValue();
        } else if (multiplicand instanceof Long || multiplier instanceof Long) {
            return multiplicand.longValue() * multiplier.longValue();
        } else {
            // int, short, and byte are promoted to int in arithmetic operations
            return multiplicand.intValue() * multiplier.intValue();
        }
    }

    private static Number divide(Number dividend, Number divisor) {
        if (dividend == null || divisor == null) {
            throw new IllegalArgumentException("Both dividend and divisor must be non-null");
        }

        if (divisor.doubleValue() == 0.0) {
            throw new ArithmeticException("Division by zero");
        }

        if (dividend instanceof BigDecimal || divisor instanceof BigDecimal) {
            return toBigDecimal(dividend).divide(toBigDecimal(divisor));
        } else if (dividend instanceof BigInteger || divisor instanceof BigInteger) {
                return toBigDecimal(dividend).divide(toBigDecimal(divisor));
        } else if (dividend instanceof Double || divisor instanceof Double) {
            return dividend.doubleValue() / divisor.doubleValue();
        } else if (dividend instanceof Float || divisor instanceof Float) {
            return dividend.floatValue() / divisor.floatValue();
        } else if (dividend instanceof Long || divisor instanceof Long) {
            return dividend.longValue() / divisor.longValue();
        } else {
            // int, short, and byte are promoted to int in arithmetic operations
            return dividend.intValue() / divisor.intValue();
        }
    }

    private static BigDecimal toBigDecimal(Number number) {
        if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        }
        if (number instanceof BigInteger) {
            return new BigDecimal((BigInteger) number);
        }
        return BigDecimal.valueOf(number.doubleValue());
    }

    private static BigInteger toBigInteger(Number number) {
        if (number instanceof BigInteger) {
            return (BigInteger) number;
        }
        return BigInteger.valueOf(number.longValue());
    }
}
