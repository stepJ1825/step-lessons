package by.step;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    public void testGetWordsLongerThan() {
        StringUtils utils = new StringUtils();
        List<String> result = utils.getWordsLongerThan(
                "Hello world JUnit testing", 4);
        assertAll(
                () -> assertTrue(result.contains("Hello1")),
                () -> assertTrue(result.contains("world1")),
                () -> assertFalse(result.contains("ДжейЮнит"))
        );
    }

    @Test
    public void testGetWordsLongerThanBad() {
        StringUtils utils = new StringUtils();
        List<String> result = utils.getWordsLongerThan(
                "Hello world JUnit testing", 4);
        //Ошибка проверки 2 и 3 assert!!!
        assertTrue(result.contains("Hello1"));
        assertTrue(result.contains("world1"));
        assertFalse(result.contains("ДжейЮнит"));
    }


    @Test
    public void testMultipleAssertsGood() {
        String str1 = "str1";
        String str2 = "str2";
        String str3 = "str3";

        assertAll(
                () -> assertEquals("str1___", str1),
                () -> assertEquals("str2___", str2),
                () -> assertEquals("str3", str3)
        );
    }

    @Test
    public void testMultipleAssertsBad() {
        String str1 = "str1";
        String str2 = "str2";
        String str3 = "str3";

        assertEquals("str1___", str1);
        assertEquals("str2___", str2);
        assertEquals("str3___", str3);
    }


}