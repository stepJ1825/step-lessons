package by.step.personexample;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Аннотация для демонстрации
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
public @interface MyAnnotation {
    String value() default "";

    int version() default 1;
}

/*
public enum RetentionPolicy {
    SOURCE,     // Аннотация сохраняется только в исходном коде
    CLASS,      // Аннотация сохраняется в байт-коде, но недоступна во время выполнения
    RUNTIME     // Аннотация сохраняется в байт-коде и доступна во время выполнения через Reflection
}
 */

