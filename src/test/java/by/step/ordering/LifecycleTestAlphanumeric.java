package by.step.ordering;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
//Сначала цифры (test1), потом буквы (testA, testB).
public class LifecycleTestAlphanumeric {

//    @BeforeEach
//    public void setup() {
//        System.out.println("Настройка перед тестом");
//    }
//
//    @AfterEach
//    public void cleanup() {
//        System.out.println("Очистка после теста");
//    }

    @Test
    public void test2() {
        System.out.println("Тест 2");
    }

    @Test
    public void testB() {
        System.out.println("Тест B");
    }

    @Test
    public void test1() {
        System.out.println("Тест 1");
    }


}