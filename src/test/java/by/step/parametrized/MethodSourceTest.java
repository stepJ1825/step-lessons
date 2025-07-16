package by.step.parametrized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodSourceTest {

    static Stream<String> stringProvider() {
        return Stream.of("Hello", "JUnit", "Testing");
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    void testStringLength(String str) {
        assertEquals(str.length(), str.length());
    }
}
