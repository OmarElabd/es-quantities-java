package org.encyclopedia.semantica.quantities.operations;

import org.encyclopedia.semantica.quantities.Quantity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;

import static org.encyclopedia.semantica.quantities.instances.SIUnits.metre;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValueProductTests {
    @Test
    @DisplayName("5m * 5 = 25m")
    public void testProductIntInt() throws ParseException {
        Quantity actual = Quantity.of(5, metre);
        actual.multiply(5);
        assertEquals(25, actual.value);
    }

    @Test
    @DisplayName("5 * 5.0 = 25.0")
    public void testProductIntDouble() throws ParseException {
        Quantity actual = Quantity.of(5, metre);
        actual.multiply(5.0);
        assertEquals(25.0, actual.value);
    }

    @Test
    @DisplayName("5.0 * 5.0 = 25.0")
    public void testProductDoubleDouble() throws ParseException {
        Quantity actual = Quantity.of(5.0, metre);
        actual.multiply(5.0);
        assertEquals(25.0, actual.value);
    }

    @Test
    @DisplayName("5 * 5f = 25.0f")
    public void testProductIntFloat() throws ParseException {
        Quantity actual = Quantity.of(5, metre);
        actual.multiply(5f);
        assertEquals(25.0f, actual.value);
    }

    @Test
    @DisplayName("5 * 5L = 25L")
    public void testProductIntLong() throws ParseException {
        Quantity actual = Quantity.of(5, metre);
        actual.multiply(5L);
        assertEquals(25L, actual.value);
    }

    @Test
    @DisplayName("5m * 5BD = 25BD")
    public void testProductIntBigDecimal() throws ParseException {
        Quantity actual = Quantity.of(5, metre);
        actual.multiply(BigDecimal.valueOf(5));
        assertEquals(BigDecimal.valueOf(25.0), actual.value);
    }

    @Test
    @DisplayName("5 * 5BI = 25BI")
    public void testProductIntBigInteger() throws ParseException {
        Quantity actual = Quantity.of(5, metre);
        actual.multiply(BigInteger.valueOf(5));
        assertEquals(BigInteger.valueOf(25), actual.value);
    }

    @Test
    @DisplayName("5.0 * 5 = 25.0")
    public void testProductDoubleInt() throws ParseException {
        Quantity actual = Quantity.of(5.0, metre);
        actual.multiply(5);
        assertEquals(25.0, actual.value);
    }

    @Test
    @DisplayName("5.0 * 5.0f = 25.0")
    public void testProductDoubleFloat() throws ParseException {
        Quantity actual = Quantity.of(5.0, metre);
        actual.multiply(5.0f);
        assertEquals(25.0, actual.value);
    }

    @Test
    @DisplayName("5.0 * 5L = 25.0")
    public void testProductDoubleLong() throws ParseException {
        Quantity actual = Quantity.of(5.0, metre);
        actual.multiply(5L);
        assertEquals(25.0, actual.value);
    }

    @Test
    @DisplayName("5.0 * 5BD = 25.0BD")
    public void testProductDoubleBigDecimal() throws ParseException {
        Quantity actual = Quantity.of(5.0, metre);
        actual.multiply(BigDecimal.valueOf(5));
        assertEquals(BigDecimal.valueOf(25.0), actual.value);
    }

    @Test
    @DisplayName("5.0 * 5BI = 25.0BD")
    public void testProductDoubleBigInteger() throws ParseException {
        Quantity actual = Quantity.of(5.0, metre);
        actual.multiply(BigInteger.valueOf(5));
        assertEquals(BigDecimal.valueOf(25.0), actual.value);
    }

    @Test
    @DisplayName("5f * 5 = 25f")
    public void testProductFloatInt() throws ParseException {
        Quantity actual = Quantity.of(5f, metre);
        actual.multiply(5);
        assertEquals(25f, actual.value);
    }

    @Test
    @DisplayName("5f * 5.0 = 25.0f")
    public void testProductFloatDouble() throws ParseException {
        Quantity actual = Quantity.of(5f, metre);
        actual.multiply(5.0);
        assertEquals(25.0, actual.value);
    }

    @Test
    @DisplayName("5f * 5f = 25f")
    public void testProductFloatFloat() throws ParseException {
        Quantity actual = Quantity.of(5f, metre);
        actual.multiply(5f);
        assertEquals(25f, actual.value);
    }

    @Test
    @DisplayName("5f * 5L = 25f")
    public void testProductFloatLong() throws ParseException {
        Quantity actual = Quantity.of(5f, metre);
        actual.multiply(5L);
        assertEquals(25f, actual.value);
    }

    @Test
    @DisplayName("5f * 5BD = 25BD")
    public void testProductFloatBigDecimal() throws ParseException {
        Quantity actual = Quantity.of(5f, metre);
        actual.multiply(BigDecimal.valueOf(5));
        assertEquals(BigDecimal.valueOf(25.0), actual.value);
    }

    @Test
    @DisplayName("5f * 5BI = 25BD")
    public void testProductFloatBigInteger() throws ParseException {
        Quantity actual = Quantity.of(5f, metre);
        actual.multiply(BigInteger.valueOf(5));
        assertEquals(BigDecimal.valueOf(25.0), actual.value);
    }

    @Test
    @DisplayName("5L * 5 = 25L")
    public void testProductLongInt() throws ParseException {
        Quantity actual = Quantity.of(5L, metre);
        actual.multiply(5);
        assertEquals(25L, actual.value);
    }

    @Test
    @DisplayName("5L * 5.0 = 25.0")
    public void testProductLongDouble() throws ParseException {
        Quantity actual = Quantity.of(5L, metre);
        actual.multiply(5.0);
        assertEquals(25.0, actual.value);
    }

    @Test
    @DisplayName("5L * 5f = 25f")
    public void testProductLongFloat() throws ParseException {
        Quantity actual = Quantity.of(5L, metre);
        actual.multiply(5f);
        assertEquals(25f, actual.value);
    }

    @Test
    @DisplayName("5L * 5L = 25L")
    public void testProductLongLong() throws ParseException {
        Quantity actual = Quantity.of(5L, metre);
        actual.multiply(5L);
        assertEquals(25L, actual.value);
    }

    @Test
    @DisplayName("5L * 5BD = 25BD")
    public void testProductLongBigDecimal() throws ParseException {
        Quantity actual = Quantity.of(5L, metre);
        actual.multiply(BigDecimal.valueOf(5));
        assertEquals(BigDecimal.valueOf(25.0), actual.value);
    }

    @Test
    @DisplayName("5L * 5BI = 25BI")
    public void testProductLongBigInteger() throws ParseException {
        Quantity actual = Quantity.of(5L, metre);
        actual.multiply(BigInteger.valueOf(5));
        assertEquals(BigInteger.valueOf(25), actual.value);
    }

    @Test
    @DisplayName("5BD * 5 = 25BD")
    public void testProductBigDecimalInt() throws ParseException {
        Quantity actual = Quantity.of(BigDecimal.valueOf(5), metre);
        actual.multiply(5);
        assertEquals(BigDecimal.valueOf(25.0), actual.value);
    }

    @Test
    @DisplayName("5BD * 5.0 = 25.0BD")
    public void testProductBigDecimalDouble() throws ParseException {
        Quantity actual = Quantity.of(BigDecimal.valueOf(5), metre);
        actual.multiply(5.0);
        assertEquals(BigDecimal.valueOf(25.0), actual.value);
    }

    @Test
    @DisplayName("5BD * 5f = 25BD")
    public void testProductBigDecimalFloat() throws ParseException {
        Quantity actual = Quantity.of(BigDecimal.valueOf(5), metre);
        actual.multiply(5f);
        assertEquals(BigDecimal.valueOf(25.0), actual.value);
    }

    @Test
    @DisplayName("5BD * 5L = 25BD")
    public void testProductBigDecimalLong() throws ParseException {
        Quantity actual = Quantity.of(BigDecimal.valueOf(5), metre);
        actual.multiply(5L);
        assertEquals(BigDecimal.valueOf(25.0), actual.value);
    }

    @Test
    @DisplayName("5BD * 5BD = 25BD")
    public void testProductBigDecimalBigDecimal() throws ParseException {
        Quantity actual = Quantity.of(BigDecimal.valueOf(5), metre);
        actual.multiply(BigDecimal.valueOf(5));
        assertEquals(BigDecimal.valueOf(25), actual.value);
    }

    @Test
    @DisplayName("5BD * 5BI = 25BD")
    public void testProductBigDecimalBigInteger() throws ParseException {
        Quantity actual = Quantity.of(BigDecimal.valueOf(5.0), metre);
        actual.multiply(BigInteger.valueOf(5));
        assertEquals(BigDecimal.valueOf(25.0), actual.value);
    }

    @Test
    @DisplayName("5BI * 5 = 25BI")
    public void testProductBigIntegerInt() throws ParseException {
        Quantity actual = Quantity.of(BigInteger.valueOf(5), metre);
        actual.multiply(5);
        assertEquals(BigInteger.valueOf(25), actual.value);
    }

    @Test
    @DisplayName("5BI * 5.0 = 25.0BD")
    public void testProductBigIntegerDouble() throws ParseException {
        Quantity actual = Quantity.of(BigInteger.valueOf(5), metre);
        actual.multiply(5.0);
        assertEquals(BigDecimal.valueOf(25.0), actual.value);
    }

    @Test
    @DisplayName("5BI * 5f = 25BD")
    public void testProductBigIntegerFloat() throws ParseException {
        Quantity actual = Quantity.of(BigInteger.valueOf(5), metre);
        actual.multiply(5f);
        assertEquals(BigDecimal.valueOf(25.0), actual.value);
    }

    @Test
    @DisplayName("5BI * 5L = 25BD")
    public void testProductBigIntegerLong() throws ParseException {
        Quantity actual = Quantity.of(BigInteger.valueOf(5), metre);
        actual.multiply(5L);
        assertEquals(BigInteger.valueOf(25), actual.value);
    }

    @Test
    @DisplayName("5BI * 5BD = 25BD")
    public void testProductBigIntegerBigDecimal() throws ParseException {
        Quantity actual = Quantity.of(BigInteger.valueOf(5), metre);
        actual.multiply(BigDecimal.valueOf(5.0));
        assertEquals(BigDecimal.valueOf(25.0), actual.value);
    }

    @Test
    @DisplayName("5BI * 5BI = 25BI")
    public void testProductBigIntegerBigInteger() throws ParseException {
        Quantity actual = Quantity.of(BigInteger.valueOf(5), metre);
        actual.multiply(BigInteger.valueOf(5));
        assertEquals(BigInteger.valueOf(25), actual.value);
    }
}

