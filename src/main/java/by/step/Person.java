package by.step;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Comparable<Person> {
    private int age;
    private String name;

    @Override
    public int compareTo(Person o) {
        return this.age - o.getAge();
    }
}