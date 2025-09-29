package by.step.retentionandtarget.target;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Inject {
    String value();
}

public class OrderServiceWithTargetField {
    @Inject("userService")
    private UserServiceWithTargetType service;
    // Корректно: аннотация применена к полю
}
