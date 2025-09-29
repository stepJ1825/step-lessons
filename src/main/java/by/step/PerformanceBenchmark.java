package by.step;

import by.step.personexample.Employee;

import java.lang.reflect.Method;

public class PerformanceBenchmark {
    private static final int ITERATIONS = 1000000;

    public static void main(String[] args) throws Exception {
        Employee emp = new Employee("Test", 25, "ID123", 50000.0);

        // Прямой вызов
        long startTime = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            emp.getName();
        }
        long directTime = System.nanoTime() - startTime;

        // Reflection вызов
        Method getNameMethod = Employee.class.getMethod("getName");
        startTime = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            getNameMethod.invoke(emp);
        }
        long reflectionTime = System.nanoTime() - startTime;

        System.out.println("Прямой вызов: " + directTime / 1000000 + " ms");
        System.out.println("Reflection вызов: " + reflectionTime / 1000000 + " ms");
        System.out.println("Reflection медленнее в " +
                           (reflectionTime / directTime) + " раз");
    }
}
