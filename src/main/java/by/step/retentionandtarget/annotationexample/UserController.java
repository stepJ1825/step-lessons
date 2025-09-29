package by.step.retentionandtarget.annotationexample;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Кастомная аннотация для логирования
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface Loggable {
    Level level() default Level.INFO;

    boolean timing() default false;
}

enum Level {
    DEBUG, INFO, WARN, ERROR
}

@Loggable(level = Level.DEBUG, timing = true)
public class UserController {

    @Loggable(level = Level.INFO)
    public void createUser(String username) {
        // логика создания пользователя
    }
}
