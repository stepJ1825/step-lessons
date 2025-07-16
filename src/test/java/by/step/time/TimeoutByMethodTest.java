package by.step.time;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

public class TimeoutByMethodTest {

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    void fastTest() throws InterruptedException {
        Thread.sleep(500); // Успеет выполниться
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void slowTest() throws InterruptedException {
        Thread.sleep(1500); // Упадёт с TimeoutException
    }
}