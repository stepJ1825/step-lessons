package by.step.classwork1;

import java.util.Collections;
import java.util.List;

public class Stream6FlatMap {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("AAA",List.of(10, 9, 8)),
                new Student("BBBdddd",List.of(1, 2, 3)),
                new Student("CCC",List.of(5, 6, 7)),
                new Student("DDD",List.of(8, 9)),
                new Student("EEE",Collections.EMPTY_LIST)
        );

        double asDouble = students.stream()
//                .map(student -> student.getMarks())
                // {10 9 8} {1 2 3} ...
                .flatMap(student -> student.getMarks().stream())
//                //10 9 8 1 2 3 5 6 7 8 9
                .mapToInt(value -> value)
                .average()
                .getAsDouble();

        students.stream()
                .flatMapToInt(student -> student.name.chars())
                .mapToObj(operand -> String.valueOf((char)operand))
                .forEach(System.out::println);


    }
}

class Student{
    String name;
    List<Integer> marks;

    public Student(String name,List<Integer> marks) {
        this.marks = marks;
        this.name = name;
    }

    public List<Integer> getMarks() {
        return marks;
    }
}
