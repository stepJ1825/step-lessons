package by.step.parametrized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValueSourceTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 10})
    public void testIsOdd(int number) {
        assertTrue(number % 2 != 0, number + " должно быть нечётным");
    }
}
