package by.step.methodtrace;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MethodTraceAutoConfiguration.class)
public @interface EnableMethodTrace {
    /**
     * Включить трейсинг для всех методов или только аннотированных
     */
    boolean traceAllMethods() default false;
}