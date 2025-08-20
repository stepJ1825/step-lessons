package by.step.classwork2.groupingBy;

import by.step.classwork2.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class GroupingByExample1 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "IT", 5000),
                new Employee("Bob", "HR", 4000),
                new Employee("Charlie", "IT", 6000),
                new Employee("Diana", "HR", 4500),
                new Employee("Eve", "Finance", 5500)
        );

        // Группировка сотрудников по отделам
        Map<String, List<Employee>> employeesByDepartment =
                employees.stream()
                         .collect(Collectors.groupingBy(Employee::getDepartment));

        System.out.println(employeesByDepartment);
        // Output: {Finance=[Eve], HR=[Bob, Diana], IT=[Alice, Charlie]}
    }
}

