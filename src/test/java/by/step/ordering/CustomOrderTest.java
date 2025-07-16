package by.step.ordering;

import org.junit.jupiter.api.*;
import java.lang.annotation.*;
import java.util.Comparator;

@TestMethodOrder(PriorityOrderer.class)
public class CustomOrderTest {

    @Test
    @Priority(3)
    void testA() {
        System.out.println("Test A (Priority 3)");
    }

    @Test
    @Priority(1)
    void testB() {
        System.out.println("Test B (Priority 1)");
    }

    @Test
    @Priority(2)
    void testC() {
        System.out.println("Test C (Priority 2)");
    }
}
