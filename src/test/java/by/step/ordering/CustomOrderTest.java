package by.step.ordering;

import org.junit.jupiter.api.*;
import java.lang.annotation.*;
import java.util.Comparator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Priority {
    int value();
}

//@TestMethodOrder(PriorityOrderer.class)
@TestMethodOrder(CustomOrderTest.CustomOrder.class)
public class CustomOrderTest {

    static class CustomOrder implements MethodOrderer {
        @Override
        public void orderMethods(MethodOrdererContext context) {
            context.getMethodDescriptors().sort(
                    Comparator.comparingInt(md ->
                            md.getMethod().getAnnotation(Priority.class).value()
                    )
            );
        }
    }

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
