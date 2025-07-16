package by.step.parametrized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DayOfWeekTest {
    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    @ParameterizedTest
//    @EnumSource(Day.class)
    @EnumSource(value = Day.class, names = {"MONDAY", "FRIDAY"})
    void testDayIsValid(Day day) {
        assertTrue(day.name().length() >= 6, day + " должен содержать минимум 6 букв");
    }
}
