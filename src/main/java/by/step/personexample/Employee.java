package by.step.personexample;

import lombok.Data;
import lombok.NoArgsConstructor;

// Класс-наследник с аннотациями
@MyAnnotation(value = "Employee class", version = 2)
@Data
@NoArgsConstructor
public class Employee extends Person {
    @MyAnnotation("employeeId field")
    private String employeeId;
    public double salary;

    public Employee(String name, int age, String employeeId, double salary) {
        super(name, age);
        this.employeeId = employeeId;
        this.salary = salary;
    }

    @MyAnnotation("work method")
    public void work() {
        System.out.println("Employee is working");
    }

    @Override
    public String toString() {
        return String.format(
                "Employee{name='%s', age=%d, id='%s', salary=%.2f}",
                getName(), getAge(), employeeId, salary
        );
    }
}
