package by.step.personexample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MyAnnotation(value = "Person class", version = 1)
public class Person implements Comparator<Person> {
    private String name;
    protected int age;

    private void privateMethod() {
        System.out.println("Это приватный метод");
    }

    @Override
    public int compare(Person o1, Person o2) {
        return 0;
    }
}
