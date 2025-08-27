package by.step.classwork2.examples.collectingAndThen;

import by.step.classwork2.model.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeAnalysis {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "IT", 5000),
                new Employee("Bob", "HR", 4000),
                new Employee("Charlie", "IT", 6000),
                new Employee("Diana", "HR", 4500),
                new Employee("Eve", "Finance", 5500)
        );

        // Средняя зарплата с округлением
        Map<String, Integer> avgSalaryRounded = employees.stream()
                                                         .collect(Collectors.groupingBy(
                                                                 Employee::getDepartment,
                                                                 Collectors.collectingAndThen(
                                                                         Collectors.averagingDouble(Employee::getSalary),
                                                                         avg -> (int) Math.round(avg) // Округление
                                                                 )
                                                         ));
        System.out.println("Средняя зарплата (округленная): " + avgSalaryRounded);

        // Сотрудники с зарплатой выше средней по отделу
        Map<String, List<String>> highEarners = employees.stream()
                                                         .collect(Collectors.groupingBy(
                                                                 Employee::getDepartment,
                                                                 Collectors.collectingAndThen(
                                                                         Collectors.toList(),
                                                                         deptEmployees -> {
                                                                             double avg = deptEmployees.stream()
                                                                                                       .mapToDouble(Employee::getSalary)
                                                                                                       .average()
                                                                                                       .orElse(0);
                                                                             return deptEmployees.stream()
                                                                                                 .filter(e -> e.getSalary() > avg)
                                                                                                 .map(Employee::getName)
                                                                                                 .collect(Collectors.toList());
                                                                         }
                                                                 )
                                                         ));
        System.out.println("Сотрудники с зарплатой выше средней: " + highEarners);
    }
}