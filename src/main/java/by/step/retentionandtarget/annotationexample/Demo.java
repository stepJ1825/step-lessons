package by.step.retentionandtarget.annotationexample;

import java.lang.annotation.*;
import java.lang.reflect.*;

// Определяем нашу аннотацию
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@interface MyAnnotation {
    String value() default "";

    int version() default 1;

    String description() default "";
}

// Применяем аннотацию к разным элементам
@MyAnnotation(value = "User Entity", version = 2, description = "Основная сущность пользователя")
class User {
    @MyAnnotation(value = "user identifier", version = 1)
    private Long id;

    @MyAnnotation("username field")
    private String username;

    @MyAnnotation(version = 1, description = "Метод сохранения пользователя")
    public void save() {
        System.out.println("Saving user: " + username);
    }

    @MyAnnotation("Метод удаления")
    public void delete() {
        System.out.println("Deleting user: " + username);
    }
}

// Обработчик аннотаций
public class Demo {
    public static void processAnnotations(Class<?> clazz) {
        System.out.println("=== ОБРАБОТКА КЛАССА: " + clazz.getSimpleName() + " ===");

        // Обработка аннотаций класса
        MyAnnotation classAnnotation = clazz.getAnnotation(MyAnnotation.class);
        if (classAnnotation != null) {
            System.out.println("Класс: " + clazz.getSimpleName());
            System.out.println("  value: " + classAnnotation.value());
            System.out.println("  version: " + classAnnotation.version());
            System.out.println("  description: " + classAnnotation.description());
        }

        // Обработка аннотаций полей
        System.out.println("\n--- ПОЛЯ ---");
        for (Field field : clazz.getDeclaredFields()) {
            MyAnnotation fieldAnnotation = field.getAnnotation(MyAnnotation.class);
            if (fieldAnnotation != null) {
                System.out.println("Поле: " + field.getName());
                System.out.println("  value: " + fieldAnnotation.value());
                System.out.println("  version: " + fieldAnnotation.version());
                System.out.println("  description: " + fieldAnnotation.description());
            }
        }

        // Обработка аннотаций методов
        System.out.println("\n--- МЕТОДЫ ---");
        for (Method method : clazz.getDeclaredMethods()) {
            MyAnnotation methodAnnotation = method.getAnnotation(MyAnnotation.class);
            if (methodAnnotation != null) {
                System.out.println("Метод: " + method.getName());
                System.out.println("  value: " + methodAnnotation.value());
                System.out.println("  version: " + methodAnnotation.version());
                System.out.println("  description: " + methodAnnotation.description());
            }
        }
    }

    public static void main(String[] args) {
        processAnnotations(User.class);
    }
}