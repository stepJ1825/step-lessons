package by.step;

import by.step.personexample.Employee;

import java.lang.reflect.Constructor;

public class DynamicObjectCreation {

    public static Object createInstance(String className, Object... params)
            throws Exception {
        Class<?> clazz = Class.forName(className);

        // Находим подходящий конструктор
        for (Constructor<?> constructor : clazz.getConstructors()) {
            if (constructor.getParameterCount() == params.length) {
                // Проверяем типы параметров (упрощенная версия)
                boolean paramsMatch = true;
                Class<?>[] paramTypes = constructor.getParameterTypes();
                for (int i = 0; i < params.length; i++) {
                    if (!isCompatible(paramTypes[i], params[i].getClass())) {
                        paramsMatch = false;
                        break;
                    }
                }

                if (paramsMatch) {
                    return constructor.newInstance(params);
                }
            }
        }

        throw new NoSuchMethodException("Не найден подходящий конструктор");
    }

    private static boolean isCompatible(Class<?> paramType, Class<?> argType) {
        // Упрощенная проверка совместимости типов
        return paramType.isAssignableFrom(argType) ||
               (paramType.isPrimitive() && isWrapperCompatible(paramType, argType));
    }

    private static boolean isWrapperCompatible(Class<?> primitive, Class<?> wrapper) {
        return (primitive == int.class && wrapper == Integer.class) ||
               (primitive == long.class && wrapper == Long.class) ||
               (primitive == double.class && wrapper == Double.class) ||
               (primitive == boolean.class && wrapper == Boolean.class);
    }

    public static void main(String[] args) throws Exception {
        // Создаем объекты динамически
        String str = (String) createInstance("java.lang.String", "Hello World");
        System.out.println("Созданная строка: " + str);

        Employee emp = (Employee) createInstance("Employee",
                "Дмитрий", 40, "EMP999", 100000.0);
        System.out.println("Созданный сотрудник: " + emp);
    }
}
