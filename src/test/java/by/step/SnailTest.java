package by.step;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SnailTest {

    @ParameterizedTest
    @CsvSource({
            "3,2,1,2",
            "10,3,1,5",
            "10,3,2,8",
            "100,20,5,7",
            "5,10,3,1"})
    void snail(int column, int day, int night, int expected) {
        int actual = Snail.snail(column, day, night);
        assertEquals(expected, actual);
    }
}