package by.step.parametrized;

import by.step.Plane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MethodSourceTest2 {

    static Stream<Arguments> stringProvider() {
        return Stream.of(
                Arguments.of(new Plane(800, 60000), 800, 60000),
                Arguments.of(new Plane(200, 1000), 200, 1000),
                Arguments.of(new Plane(1, 1), 200, 1000),
                Arguments.of(new Plane(0, 0), new Plane(0, 0))
        );
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    void testGetPlainSpeed(Plane plane, int speed, int weight) {
        assertAll(
                () -> assertEquals(plane.getSpeed(), speed),
                () -> assertEquals(plane.getWeight(), weight)
        );
    }

    static Stream<Arguments> stringProvider2() {
        return Stream.of(
                Arguments.of(new Plane(800, 0),
                        new Plane(800, 0))
        );
    }

    @ParameterizedTest
    @MethodSource("stringProvider2")
    void testPlainEquality(Plane plane1, Plane plane2) {
        assertEquals(plane1, plane2);
    }

}
