package by.step;

import java.util.HashMap;
import java.util.Map;

public class WrapperCompatibilityChecker {

    /**
     * Проверяет совместимость примитивного типа и его wrapper-класса
     * @param primitive - примитивный тип (int.class, boolean.class и т.д.)
     * @param wrapper - класс-обертка (Integer.class, Boolean.class и т.д.)
     * @return true если типы совместимы
     */
    public static boolean isWrapperCompatible(Class<?> primitive, Class<?> wrapper) {
        // Проверяем все возможные комбинации примитивных типов и их оберток

        if (primitive == int.class && wrapper == Integer.class) {
            return true;
        }
        if (primitive == long.class && wrapper == Long.class) {
            return true;
        }
        if (primitive == double.class && wrapper == Double.class) {
            return true;
        }
        if (primitive == boolean.class && wrapper == Boolean.class) {
            return true;
        }
        if (primitive == char.class && wrapper == Character.class) {
            return true;
        }
        if (primitive == byte.class && wrapper == Byte.class) {
            return true;
        }
        if (primitive == short.class && wrapper == Short.class) {
            return true;
        }
        if (primitive == float.class && wrapper == Float.class) {
            return true;
        }
        if (primitive == void.class && wrapper == Void.class) {
            return true;
        }

        return false;
    }

    /**
     * Улучшенная версия с использованием Map для лучшей производительности
     */
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TO_WRAPPER = new HashMap<>();
    private static final Map<Class<?>, Class<?>> WRAPPER_TO_PRIMITIVE = new HashMap<>();

    static {
        // Инициализируем mapping примитивных типов и их оберток
        PRIMITIVE_TO_WRAPPER.put(int.class, Integer.class);
        PRIMITIVE_TO_WRAPPER.put(long.class, Long.class);
        PRIMITIVE_TO_WRAPPER.put(double.class, Double.class);
        PRIMITIVE_TO_WRAPPER.put(boolean.class, Boolean.class);
        PRIMITIVE_TO_WRAPPER.put(char.class, Character.class);
        PRIMITIVE_TO_WRAPPER.put(byte.class, Byte.class);
        PRIMITIVE_TO_WRAPPER.put(short.class, Short.class);
        PRIMITIVE_TO_WRAPPER.put(float.class, Float.class);
        PRIMITIVE_TO_WRAPPER.put(void.class, Void.class);

        // Обратный mapping
        for (Map.Entry<Class<?>, Class<?>> entry : PRIMITIVE_TO_WRAPPER.entrySet()) {
            WRAPPER_TO_PRIMITIVE.put(entry.getValue(), entry.getKey());
        }
    }

    /**
     * Оптимизированная версия с использованием HashMap
     */
    private static boolean isWrapperCompatibleOptimized(Class<?> primitive, Class<?> wrapper) {
        Class<?> expectedWrapper = PRIMITIVE_TO_WRAPPER.get(primitive);
        return expectedWrapper != null && expectedWrapper == wrapper;
    }

    /**
     * Универсальный метод, который работает в обе стороны
     */
    public static boolean areTypesCompatible(Class<?> type1, Class<?> type2) {
        // Если типы одинаковые - совместимы
        if (type1 == type2) {
            return true;
        }

        // Проверяем примитив -> wrapper
        if (type1.isPrimitive() && !type2.isPrimitive()) {
            Class<?> expectedWrapper = PRIMITIVE_TO_WRAPPER.get(type1);
            return type2 == expectedWrapper;
        }

        // Проверяем wrapper -> примитив
        if (!type1.isPrimitive() && type2.isPrimitive()) {
            Class<?> expectedPrimitive = WRAPPER_TO_PRIMITIVE.get(type1);
            return type2 == expectedPrimitive;
        }

        // Если оба не примитивные, проверяем наследование
        if (!type1.isPrimitive() && !type2.isPrimitive()) {
            return type2.isAssignableFrom(type1) || type1.isAssignableFrom(type2);
        }

        return false;
    }
}