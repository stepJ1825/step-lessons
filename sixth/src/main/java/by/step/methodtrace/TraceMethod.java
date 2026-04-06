package by.step.methodtrace;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TraceMethod {
    /**
     * Кастомное сообщение для лога
     */
    String value() default "";

    /**
     * Переопределить порог для конкретного метода
     */
    long thresholdMs() default -1;

    /**
     * Скрыть параметры для этого метода
     */
    boolean hideParameters() default false;

    /**
     * Скрыть результат для этого метода
     */
    boolean hideResult() default false;
}
