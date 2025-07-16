package by.step.ordering;

import org.junit.jupiter.api.*;

//@TestMethodOrder(MethodOrderer.DisplayName.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DisplayNameOrderTest {

    @Test
    @DisplayName("Third Test. Всё, пока!")
    void testC() {
        System.out.println("Test C");
    }

    @Test
    @DisplayName("First Test")
    void testA() {
        System.out.println("Test A");
    }

    @Test
    @DisplayName("Second Test")
    @Order(1)
    void testB() {
        System.out.println("Test B");
    }

    @Test
    @DisplayName("Этот тест ничего не делает и ничего не тестирует")
    void testNothing() {
    }
}
