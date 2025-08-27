package by.step.classwork2.examples.collectingAndThen;

import java.util.*;
import java.util.stream.Collectors;

public class CollectingAndThenExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Без collectingAndThen - получаем Optional
        Optional<Integer> maxOptional = numbers.stream()
                                               .collect(Collectors.maxBy(Integer::compareTo));
        System.out.println("Максимум (Optional): " + maxOptional);

        // С collectingAndThen - получаем непосредственно значение
        Integer maxValue = numbers.stream()
                                  .collect(Collectors.collectingAndThen(
                                          Collectors.maxBy(Integer::compareTo),
                                          optional -> optional.orElse(0) // Преобразуем Optional в Integer
                                  ));
        System.out.println("Максимум (значение): " + maxValue);
    }
}
