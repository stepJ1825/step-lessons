package by.step.time.extension;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TimingExtension.class)
public class ExtendedTimingTest {
    @Test
    void someTest() throws InterruptedException {
        Thread.sleep(200);
    }
}