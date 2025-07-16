package by.step;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    public void testAddPositiveNumbers() {
        Calculator calculator = new Calculator();
        int actual = calculator.add(2, 3);
        int expected = 5;
        assertEquals(expected, actual, "2 + 3 должно быть 5");
    }

    @Test
    public void testAddNegativeNumbers() {
        Calculator calculator = new Calculator();
        int actual = calculator.add(-2, -3);
        int expected = -5;
        assertEquals(expected, actual);
    }

    @Test
    public void testMultiplyForPositiveIntegers() {
        Calculator calculator = new Calculator();
        int expected = 10;
        int actual = calculator.multiply(5, 2);
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    public void testBigIntegerMultiply() {
        Calculator calculator = new Calculator();
        BigInteger bigInteger1 =
                new BigInteger("1234567890123456789012345678912345612345678901234567890123456789123456");
        BigInteger bigInteger2 =
                new BigInteger("9876541234567894561234567894561234598765412345678945612345678945612345");
        calculator.multiply(bigInteger1, bigInteger2);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    public void testBigIntegerPower() {
        Calculator calculator = new Calculator();
        BigInteger bigInteger =
                new BigInteger("1234567890123456789012345678912345612345678901234567890123456789123456");
        calculator.powerBigInt(bigInteger, 1_000);
    }
}