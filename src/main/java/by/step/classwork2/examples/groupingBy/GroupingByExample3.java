package by.step.classwork2.examples.groupingBy;

import by.step.classwork2.model.Employee;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class GroupingByExample3 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "IT", 5000),
                new Employee("Bob", "HR", 4000),
                new Employee("Charlie", "IT", 6000),
                new Employee("Diana", "HR", 4500),
                new Employee("Eve", "Finance", 5500),
                new Employee("Frank", "IT", 5200),
                new Employee("Frank", "IT", 5200)
        );

        // Пример 1: TreeMap для сортировки по названию отдела
        Map<String, List<Employee>> sortedByDept = employees.stream()
                                                            .collect(Collectors.groupingBy(
                                                                    Employee::getDepartment,
                                                                    TreeMap::new,  // Отсортированная Map по ключам
                                                                    Collectors.toList()
                                                            ));
        System.out.println("Отсортировано по отделам: " + sortedByDept);

        // Пример 2: LinkedHashMap для сохранения порядка обработки
        Map<String, Double> linkedAvgSalary = employees.stream()
                                                       .collect(Collectors.groupingBy(
                                                               Employee::getDepartment,
                                                               LinkedHashMap::new,  // Сохраняет порядок вставки
                                                               Collectors.averagingDouble(Employee::getSalary)
                                                       ));
        System.out.println("Средняя зарплата (порядок сохранен): " + linkedAvgSalary);

        // Пример 3: ConcurrentMap для потокобезопасности
        Map<String, Set<String>> concurrentNames = employees.stream()
                                                            .collect(Collectors.groupingBy(
                                                                    Employee::getDepartment,
                                                                    ConcurrentHashMap::new,  // Потокобезопасная
                                                                    // Map
                                                                    Collectors.mapping(Employee::getName, Collectors.toSet())
                                                            ));
        System.out.println("Уникальные имена (ConcurrentMap): " + concurrentNames);
    }
}
