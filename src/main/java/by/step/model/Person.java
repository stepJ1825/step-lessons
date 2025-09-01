package by.step.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
    private int id;
    private String firstName;

    public String getFirstName() {
        System.err.println("Person class, in getter method ");
        return firstName;
    }

}
