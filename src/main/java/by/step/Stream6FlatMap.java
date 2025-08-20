package by.step;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Stream6FlatMap {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student(List.of(10, 9, 8)),
                new Student(List.of(1, 2, 3)),
                new Student(List.of(5, 6, 7)),
                new Student(List.of(8, 9, 8)),
                new Student(Collections.EMPTY_LIST)
        );

        double asDouble = students.stream()
                .flatMap(student -> student.getMarks().stream())
                .mapToInt(value -> value)
                .average()
                .getAsDouble();


    }
}

class Student{
    List<Integer> marks;

    public Student(List<Integer> marks) {
        this.marks = marks;
    }

    public List<Integer> getMarks() {
        return marks;
    }
}
