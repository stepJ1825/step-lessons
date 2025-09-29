package by.step.retentionandtarget.target;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Cacheable {
    int ttl() default 3600;
}

public class DataServiceWithTargetMethod {
    @Cacheable(ttl = 1800)
    public String getData() {
        return "cached data";
    }
    // Корректно: аннотация применена к методу
}
