package by.step.classwork2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    private String name;
    private String department;
    private double salary;

    // конструктор, геттеры, toString
}
