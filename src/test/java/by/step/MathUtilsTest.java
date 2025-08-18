package by.step;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MathUtilsTest {

    @Test
    public void testDivide() {
        MathUtils mathUtils = new MathUtils();
        assertEquals(2.5, mathUtils.divide(5, 2));
    }

    @Test
    public void testDivideByZero() {
        MathUtils mathUtils = new MathUtils();
        assertThrows(ArithmeticException.class, () -> mathUtils.divide(5, 0));
    }

    @Test
    void testGoodDivide(){
        MathUtils mathUtils = new MathUtils();
        assertEquals(2, mathUtils.divide(10,5));
    }
}