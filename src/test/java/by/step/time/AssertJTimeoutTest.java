package by.step.time;

import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Преимущества:
 * Более читаемый синтаксис.
 * Можно проверять блоки кода, а не только целые тесты.
 */
public class AssertJTimeoutTest {

//    @Test
//    void testWithAssertJ() {
//        assertThatCode(() -> {
//            // Код, который должен выполниться быстро
//            Thread.sleep(500);
//        }).succeedsWithin(Duration.ofSeconds(1));
//    }
}