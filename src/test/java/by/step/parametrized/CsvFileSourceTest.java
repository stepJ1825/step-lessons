package by.step.parametrized;

import by.step.Calculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvFileSourceTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/csvtest/test-data.csv", numLinesToSkip = 1)
    void testMultiply(int a, int b, int expected) {
        Calculator calculator = new Calculator();
        assertEquals(expected, calculator.multiply(a, b));
    }

}
