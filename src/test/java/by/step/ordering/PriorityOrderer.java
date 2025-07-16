package by.step.ordering;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrdererContext;
import java.util.Comparator;

public class PriorityOrderer implements MethodOrderer {
    @Override
    public void orderMethods(MethodOrdererContext context) {
        context.getMethodDescriptors().sort(
                Comparator.comparingInt(
                        descriptor -> descriptor.getMethod()
                                                .getAnnotation(Priority.class)
                                                .value()
                )
        );
    }
}
