package by.step.ordering;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class DisplayNameOrderTest {

    @Test
    @DisplayName("Third Test")
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
    void testB() {
        System.out.println("Test B");
    }
}
