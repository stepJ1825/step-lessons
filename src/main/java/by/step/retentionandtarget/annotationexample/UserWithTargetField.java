package by.step.retentionandtarget.annotationexample;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для валидации
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Valid {
    int minLength() default 0;

    int maxLength() default Integer.MAX_VALUE;

    String regex() default "";
}

public class UserWithTargetField {
    @Valid(minLength = 3, maxLength = 50)
    private String username;

    @Valid(regex = "^[\\w.%+-]+@[\\w.-]+\\.[A-Z]{2,}$")
    private String email;
}
