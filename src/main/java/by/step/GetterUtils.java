package by.step;

import by.step.personexample.Employee;

import java.lang.reflect.*;
import java.util.*;

public class GetterUtils {

    /**
     * Вызывает все геттеры объекта и возвращает map с результатами
     */
    public static Map<String, Object> invokeGetters(Object obj) throws Exception {
        Map<String, Object> result = new HashMap<>();
        Class<?> clazz = obj.getClass();

        for (Method method : clazz.getMethods()) {
            // Проверяем, является ли метод геттером
            if (isGetter(method)) {
                String fieldName = getFieldNameFromGetter(method.getName());
                Object value = method.invoke(obj);
                result.put(fieldName, value);
            }
        }

        return result;
    }

    private static boolean isGetter(Method method) {
        return (method.getName().startsWith("get") ||
                method.getName().startsWith("is")) &&
               method.getParameterCount() == 0 &&
               !method.getReturnType().equals(void.class) &&
               Modifier.isPublic(method.getModifiers());
    }

    private static String getFieldNameFromGetter(String getterName) {
        if (getterName.startsWith("get")) {
            return Character.toLowerCase(getterName.charAt(3)) +
                   getterName.substring(4);
        } else if (getterName.startsWith("is")) {
            return Character.toLowerCase(getterName.charAt(2)) +
                   getterName.substring(3);
        }
        return getterName;
    }

    public static void main(String[] args) throws Exception {
        Employee emp = new Employee("Сергей", 45, "EMP111", 120000.0);
        Map<String, Object> properties = invokeGetters(emp);

        System.out.println("Свойства объекта:");
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
    }
}
