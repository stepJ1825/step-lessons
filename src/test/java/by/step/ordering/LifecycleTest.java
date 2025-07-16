package by.step.ordering;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Включаем поддержку @Order
public class LifecycleTest {

    @BeforeEach
    public void setup() {
        System.out.println("Настройка перед тестом");
    }

    @AfterEach
    public void cleanup() {
        System.out.println("Очистка после теста");
    }

    @Test
    @Order(2)
    public void test1() {
        System.out.println("Тест 1");
    }

    @Test
    @Order(1)
    public void test2() {
        System.out.println("Тест 2");
    }
}