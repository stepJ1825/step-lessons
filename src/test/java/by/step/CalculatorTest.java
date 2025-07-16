package by.step;

import org.junit.jupiter.api.Test;

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
}