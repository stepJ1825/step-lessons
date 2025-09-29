package by.step.retentionandtarget.target;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Service {
    String name();
    boolean lazyInit() default false;
}

@Service(name = "userService", lazyInit = true)
public class UserServiceWithTargetType {
    // Корректно: аннотация применена к классу
}