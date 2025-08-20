package by.step.classwork1;

import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream5PersonExample {
    public static void main(String[] args) {
        OptionalDouble average = Stream.of(
                        new Person("AAA", 10),
                        new Person("BBB", 20),
                        new Person("CCC", 40),
                        new Person("DDD", 30)
                ).mapToInt(person -> person.age)
                .average();
        System.out.println(average);

        IntStream.range(0,10)
                .mapToObj(value -> value + 10)
                .forEach(System.out::println);

    }
}

class Person{
    String name;
    Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
