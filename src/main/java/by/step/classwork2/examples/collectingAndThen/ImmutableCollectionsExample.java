package by.step.classwork2.examples.collectingAndThen;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ImmutableCollectionsExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Diana");

        // Создание неизменяемого списка
        List<String> immutableList = names.stream()
                                          .filter(name -> name.length() > 4)

                                          .collect(Collectors.collectingAndThen(
                                                  Collectors.toList(),
                                                  Collections::unmodifiableList
                                          ));

        System.out.println("Неизменяемый список: " + immutableList);

        // Попытка изменить выбросит UnsupportedOperationException
        // immutableList.add("Eve"); // Ошибка!
    }
}