package by.step.parametrized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvFileSourceTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testMultiply(int a, int b, int expected) {
        assertEquals(expected, a * b, "Умножение " + a + " на " + b);
    }

}
