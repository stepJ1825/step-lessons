package by.step;

import java.util.ArrayList;
import java.util.List;

public class WrapperCompatibilityTest {

    public static void main(String[] args) {
        System.out.println("=== ТЕСТИРОВАНИЕ СОВМЕСТИМОСТИ ТИПОВ ===\n");

        // Тест примитив -> wrapper
        testCompatibility(int.class, Integer.class, true, "int -> Integer");
        testCompatibility(long.class, Long.class, true, "long -> Long");
        testCompatibility(boolean.class, Boolean.class, true, "boolean -> Boolean");
        testCompatibility(char.class, Character.class, true, "char -> Character");
        testCompatibility(byte.class, Byte.class, true, "byte -> Byte");
        testCompatibility(short.class, Short.class, true, "short -> Short");
        testCompatibility(float.class, Float.class, true, "float -> Float");
        testCompatibility(double.class, Double.class, true, "double -> Double");
        testCompatibility(void.class, Void.class, true, "void -> Void");

        // Тест несовместимых комбинаций
        testCompatibility(int.class, Long.class, false, "int -> Long (несовместимы)");
        testCompatibility(boolean.class, Integer.class, false, "boolean -> Integer (несовместимы)");
        testCompatibility(double.class, Float.class, false, "double -> Float (несовместимы)");

        // Тест wrapper -> примитив (обратная совместимость)
        testCompatibility(Integer.class, int.class, true, "Integer -> int");
        testCompatibility(Boolean.class, boolean.class, true, "Boolean -> boolean");

        // Тест одинаковых типов
        testCompatibility(String.class, String.class, true, "String -> String");
        testCompatibility(int.class, int.class, true, "int -> int");

        // Тест наследования
        testCompatibility(ArrayList.class, List.class, true, "ArrayList -> List");
        testCompatibility(String.class, Object.class, true, "String -> Object");
    }

    private static void testCompatibility(Class<?> type1, Class<?> type2,
            boolean expected, String description) {
        boolean result = WrapperCompatibilityChecker.areTypesCompatible(type1, type2);
        String status = result == expected ? "✅ ПРОЙДЕН" : "❌ НЕ ПРОЙДЕН";

        System.out.printf("%s: %s = %s (ожидалось: %s)%n",
                status, description, result, expected);
    }
}