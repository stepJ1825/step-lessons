package by.step.classwork2.tasks;

import by.step.classwork2.model.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stream14Students {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("John", "CS", 85),
                new Student("Alice", "Math", 92),
                new Student("Bob", "CS", 78),
                new Student("Eve", "Math", 88),
                new Student("Charlie", "Physics", 95)
        );

        // 1. Сгруппируйте студентов по специальностям
        // 2. Найдите средний балл для каждой специальности //TODO
        // 3. Получите список студентов с баллом выше 90 для каждой специальности //TODO

        Map<Object, List<Student>> collect1 = students.stream().collect(Collectors.groupingBy(Student::getMajor));

        System.out.println();
    }
}
