package by.step.time;

import org.junit.jupiter.api.Test;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManualTimeMeasurementTest {

    @Test
    void measureExecutionTime() {
        Instant start = Instant.now();

        // Код теста
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Instant end = Instant.now();
        long durationMs = end.toEpochMilli() - start.toEpochMilli();

        System.out.println("Тест выполнился за " + durationMs + " мс");
        assertTrue(durationMs < 500, "Тест должен выполняться быстрее 500 мс");
    }
}
