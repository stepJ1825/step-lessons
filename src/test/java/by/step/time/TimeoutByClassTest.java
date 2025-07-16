package by.step.time;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

@Timeout(value = 2, unit = TimeUnit.SECONDS) // Лимит для всех методов
public class TimeoutByClassTest {

    @Test
    void testA() throws InterruptedException {
        Thread.sleep(1000); // Успеет
    }

    @Test
    void testB() throws InterruptedException {
        Thread.sleep(2500); // Упадёт
    }
}