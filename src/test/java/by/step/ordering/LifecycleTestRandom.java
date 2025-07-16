package by.step.ordering;

import org.junit.jupiter.api.*;

/**
 * Полезно для выявления зависимостей между тестами (если тесты вдруг начали падать).
 * Порядок меняется при каждом запуске.
 * Можно задать seed для воспроизводимости:
 * @TestMethodOrder(MethodOrderer.Random.class)
 * @TestClassOrder(ClassOrderer.Random.class)  // Если нужно рандомизировать классы
 */
@TestMethodOrder(MethodOrderer.Random.class)
public class LifecycleTestRandom {

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
    public void test1() {
        System.out.println("Тест 1");
    }

    @Test
    public void test2() {
        System.out.println("Тест 2");
    }
}