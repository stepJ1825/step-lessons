package by.step.classwork2.groupingBy;

import by.step.classwork2.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class GroupingByExample2 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "IT", 5000),
                new Employee("Bob", "HR", 4000),
                new Employee("Charlie", "IT", 6000),
                new Employee("Diana", "HR", 4500),
                new Employee("Eve", "Finance", 5500)
        );

        // Пример 1: Средняя зарплата по отделам
        Map<String, Double> avgSalaryByDept = employees.stream()
                                                       .collect(Collectors.groupingBy(
                                                               Employee::getDepartment,
                                                               Collectors.averagingDouble(Employee::getSalary)
                                                       ));
        System.out.println("Средняя зарплата: " + avgSalaryByDept);

        // Пример 2: Количество сотрудников в каждом отделе
        Map<String, Long> employeeCountByDept = employees.stream()
                                                         .collect(Collectors.groupingBy(
                                                                 Employee::getDepartment,
                                                                 Collectors.counting()
                                                         ));
        System.out.println("Количество сотрудников: " + employeeCountByDept);

        // Пример 3: Список имен сотрудников по отделам
        Map<String, List<String>> namesByDept = employees.stream()
                                                         .collect(Collectors.groupingBy(
                                                                 Employee::getDepartment,
                                                                 Collectors.mapping(Employee::getName, Collectors.toList())
                                                         ));
        System.out.println("Имена по отделам: " + namesByDept);

        // Пример 4: Максимальная зарплата в каждом отделе
        Map<String, Optional<Employee>> maxSalaryByDept = employees.stream()
                                                                   .collect(Collectors.groupingBy(
                                                                           Employee::getDepartment,
                                                                           Collectors.maxBy(Comparator.comparing(Employee::getSalary))
                                                                   ));
        System.out.println("Макс. зарплата: " + maxSalaryByDept);
    }
}
