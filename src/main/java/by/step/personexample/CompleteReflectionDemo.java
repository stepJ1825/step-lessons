package by.step.personexample;

import java.lang.reflect.*;
import java.util.Arrays;

public class CompleteReflectionDemo {

    public static void main(String[] args) throws Exception {
        // Получаем класс Employee
        Class<Employee> employeeClass = Employee.class;

        System.out.println("=== ИНФОРМАЦИЯ О КЛАССЕ ===");
        analyzeClass(employeeClass);

        System.out.println("\n=== РАБОТА С КОНСТРУКТОРАМИ ===");
        workWithConstructors(employeeClass);

        System.out.println("\n=== РАБОТА С ПОЛЯМИ ===");
        workWithFields(employeeClass);

        System.out.println("\n=== РАБОТА С МЕТОДАМИ ===");
        workWithMethods(employeeClass);

        System.out.println("\n=== АНАЛИЗ АННОТАЦИЙ ===");
        analyzeAnnotations(employeeClass);
        analyzeAnnotations(Person.class);
    }

    // Анализ информации о классе
    public static void analyzeClass(Class<?> clazz) {
        System.out.println("Имя класса: " + clazz.getName());
        System.out.println("Простое имя: " + clazz.getSimpleName());
        System.out.println("Модификаторы: " + Modifier.toString(clazz.getModifiers()));
        System.out.println("Суперкласс: " + clazz.getSuperclass().getName());

        System.out.println("Интерфейсы: ");
        for (Class<?> iface : clazz.getSuperclass().getInterfaces()) {
            System.out.println("  - " + iface.getName());
        }

        System.out.println("Поля: ");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println("  - " + Modifier.toString(field.getModifiers()) +
                               " " + field.getType().getSimpleName() + " " + field.getName());
        }

        System.out.println("Методы: ");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println("  - " + Modifier.toString(method.getModifiers()) +
                               " " + method.getReturnType().getSimpleName() + " " +
                               method.getName() + Arrays.toString(method.getParameters()));
        }
    }

    // Работа с конструкторами
    public static void workWithConstructors(Class<Employee> clazz) throws Exception {
        // Получаем все конструкторы
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        System.out.println("Конструкторы:");
        for (Constructor<?> constructor : constructors) {
            System.out.println("  " + constructor);
        }

        // Создаем объект с помощью конструктора по умолчанию
        Constructor<Employee> defaultConstructor = clazz.getDeclaredConstructor();
        Employee emp1 = defaultConstructor.newInstance();
        emp1.setName("Иван");
        emp1.setAge(25);
        System.out.println("Создан через конструктор по умолчанию: " + emp1);

        // Создаем объект с помощью параметризованного конструктора
        Constructor<Employee> paramConstructor = clazz.getDeclaredConstructor(
                String.class, int.class, String.class, double.class
        );
        Employee emp2 = paramConstructor.newInstance("Петр", 30, "EMP123", 50000.0);
        System.out.println("Создан через параметризованный конструктор: " + emp2);
    }

    // Работа с полями
    public static void workWithFields(Class<Employee> clazz) throws Exception {
        Employee employee = new Employee("Мария", 28, "EMP456",
                60000.0);


        // Доступ к публичному полю
        Field publicField = clazz.getField("salary");
        System.out.println("Публичное поле salary: " + publicField.get(employee));
        publicField.set(employee, 70000.0);
        System.out.println("После изменения: " + publicField.get(employee));

        // Доступ к приватному полю
        Field privateField = clazz.getDeclaredField("employeeId");
        privateField.setAccessible(true); // Разрешаем доступ к приватному полю
        System.out.println("Приватное поле employeeId: " + privateField.get(employee));
        privateField.set(employee, "NEW123");
        System.out.println("После изменения: " + privateField.get(employee));

        // Доступ к унаследованному приватному полю
        Field inheritedPrivateField = Person.class.getDeclaredField("name");
        inheritedPrivateField.setAccessible(true);
        System.out.println("Унаследованное приватное поле name: " +
                           inheritedPrivateField.get(employee));
    }

    // Работа с методами
    public static void workWithMethods(Class<Employee> clazz) throws Exception {
        Employee employee = new Employee("Анна", 32, "EMP789", 80000.0);

        // Вызов публичного метода
        Method workMethod = clazz.getMethod("work");
        workMethod.invoke(employee);

        // Вызов унаследованного метода
        Method getNameMethod = Person.class.getDeclaredMethod("getName");
        String name = (String) getNameMethod.invoke(employee);
        System.out.println("Имя сотрудника: " + name);

        // Вызов приватного метода родительского класса
        Method privateMethod = Person.class.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true);
        privateMethod.invoke(employee);

        // Вызов метода с параметрами
        Method setSalaryMethod = clazz.getMethod("setAge", int.class);
        setSalaryMethod.invoke(employee, 35);
        System.out.println("После изменения возраста: " + employee);
    }

    // Анализ аннотаций
    public static void analyzeAnnotations(Class<? super Employee> clazz) {
//    public static void analyzeAnnotations(Class<? extends Person> clazz) {    //PECS
        // Аннотации класса
        MyAnnotation classAnnotation = clazz.getAnnotation(MyAnnotation.class);
        if (classAnnotation != null) {
            System.out.println("Аннотация класса: value=" + classAnnotation.value() +
                               ", version=" + classAnnotation.version());
        }

        // Аннотации полей
        for (Field field : clazz.getDeclaredFields()) {
            MyAnnotation fieldAnnotation = field.getAnnotation(MyAnnotation.class);
            if (fieldAnnotation != null) {
                System.out.println("Аннотация поля " + field.getName() +
                                   ": " + fieldAnnotation.value());
            }
        }

        // Аннотации методов
        for (Method method : clazz.getDeclaredMethods()) {
            MyAnnotation methodAnnotation = method.getAnnotation(MyAnnotation.class);
            if (methodAnnotation != null) {
                System.out.println("Аннотация метода " + method.getName() +
                                   ": " + methodAnnotation.value());
            }
        }
    }
}
