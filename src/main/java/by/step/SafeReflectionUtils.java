package by.step;

import java.lang.reflect.Field;
import java.util.Optional;

public class SafeReflectionUtils {

    public static Optional<Object> getFieldValue(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return Optional.of(field.get(obj));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.err.println("Ошибка доступа к полю: " + e.getMessage());
            return Optional.empty();
        }
    }

    public static boolean setFieldValue(Object obj, String fieldName, Object value) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

            // Проверка типа
            if (isCompatibleType(field.getType(), value.getClass())) {
                field.set(obj, value);
                return true;
            } else {
                System.err.println("Несовместимые типы: " +
                                   field.getType() + " и " + value.getClass());
                return false;
            }
        } catch (Exception e) {
            System.err.println("Ошибка установки поля: " + e.getMessage());
            return false;
        }
    }

    private static boolean isCompatibleType(Class<?> fieldType, Class<?> valueType) {
        return fieldType.isAssignableFrom(valueType) ||
               (fieldType.isPrimitive() && isWrapperCompatible(fieldType, valueType));
    }

    private static boolean isWrapperCompatible(Class<?> primitive, Class<?> wrapper) {
        // ... реализация как в предыдущем примере
        return true;
    }
}
