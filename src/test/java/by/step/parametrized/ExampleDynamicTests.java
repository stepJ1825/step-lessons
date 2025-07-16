package by.step.parametrized;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class ExampleDynamicTests {
    /**
     * @TestFactory — метод возвращает Stream<DynamicTest>.
     * dynamicTest() — создаёт тест с именем и лямбдой.
     * @return
     */
    @TestFactory
    Stream<DynamicTest> dynamicTests() {
        return Stream.of(2, 4, 6, 8)
                     .map(number -> dynamicTest(
                             "Проверка чётности " + number,
                             () -> assertTrue(number % 2 == 0)
                     ));
    }
}
