package by.step.retentionandtarget.retentionpolicy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface ApiVersion {
    int major();

    int minor();
}

@ApiVersion(major = 1, minor = 2)
public class UserServiceWithRuntimePolicy {
    // Аннотация доступна через Reflection API
}


//Пример использования RUNTIME аннотации:
class AnnotationProcessor {
    public static void main(String[] args) {
        Class<UserServiceWithRuntimePolicy> clazz = UserServiceWithRuntimePolicy.class;
        ApiVersion annotation = clazz.getAnnotation(ApiVersion.class);

        if (annotation != null) {
            System.out.println("API Version: " + annotation.major() + "." + annotation.minor());
            // Вывод: API Version: 1.2
        }
    }
}