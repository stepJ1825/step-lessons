package by.step.personexample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name;
    protected int age;

    private void privateMethod() {
        System.out.println("Это приватный метод");
    }
}
