package by.step;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    public void testGetWordsLongerThan() {
        StringUtils utils = new StringUtils();
        List<String> result = utils.getWordsLongerThan("Hello world JUnit testing", 4);
        assertAll(
                () -> assertTrue(result.contains("Hello")),
                () -> assertTrue(result.contains("world")),
                () -> assertFalse(result.contains("JUnit "))
        );
    }
}