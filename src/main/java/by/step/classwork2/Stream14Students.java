package by.step.classwork2;

import by.step.classwork2.model.Student;

import java.util.Arrays;
import java.util.List;

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
        // 2. Найдите средний балл для каждой специальности
        // 3. Получите список студентов с баллом выше 90 для каждой специальности
    }
}
