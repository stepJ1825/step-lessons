package by.step.classwork2.collectingAndThen;

import by.step.classwork2.model.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentAnalysis {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("John", "CS", 85),
                new Student("Alice", "Math", 92),
                new Student("Bob", "CS", 78),
                new Student("Eve", "Math", 88),
                new Student("Charlie", "Physics", 95),
                new Student("David", "CS", 91)
        );

        // Статистика по специальностям с оценкой качества
        Map<String, String> majorStats = students.stream()
                                                 .collect(Collectors.groupingBy(
                                                         Student::getMajor,
                                                         Collectors.collectingAndThen(
                                                                 Collectors.toList(),
                                                                 list -> {
                                                                     double avg = list.stream()
                                                                                      .mapToInt(Student::getScore)
                                                                                      .average()
                                                                                      .orElse(0);
                                                                     long excellent = list.stream()
                                                                                          .filter(s -> s.getScore() >= 90)
                                                                                          .count();
                                                                     return String.format("Средний: %.1f, Отличников: %d", avg, excellent);
                                                                 }
                                                         )
                                                 ));
        System.out.println("Статистика по специальностям: " + majorStats);

        // Студенты с оценкой выше среднего по специальности
        Map<String, List<String>> aboveAverageStudents = students.stream()
                                                                 .collect(Collectors.groupingBy(
                                                                         Student::getMajor,
                                                                         Collectors.collectingAndThen(
                                                                                 Collectors.toList(),
                                                                                 list -> {
                                                                                     double avg = list.stream()
                                                                                                      .mapToInt(Student::getScore)
                                                                                                      .average()
                                                                                                      .orElse(0);
                                                                                     return list.stream()
                                                                                                .filter(s -> s.getScore() > avg)
                                                                                                .map(Student::getName)
                                                                                                .collect(Collectors.toList());
                                                                                 }
                                                                         )
                                                                 ));
        System.out.println("Студенты выше среднего: " + aboveAverageStudents);
    }
}
