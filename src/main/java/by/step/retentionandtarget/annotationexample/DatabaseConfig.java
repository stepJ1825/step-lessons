package by.step.retentionandtarget.annotationexample;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для конфигурации
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Configuration {
    String prefix();

    boolean reloadable() default false;
}

@Configuration(prefix = "app.database", reloadable = true)
public class DatabaseConfig {
    // конфигурация базы данных
}
