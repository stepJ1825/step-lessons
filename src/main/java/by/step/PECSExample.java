package by.step;

import java.util.*;

/**
 * Пример 1: Копирование списков
 */
public class PECSExample {

    // Producer Extends - только читаем из source
    public static <T> void copy(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<Number> numbers = new ArrayList<>();

        // Integer extends Number, поэтому работает
        copy(integers, numbers);
        System.out.println(numbers); // [1, 2, 3]

        List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3);
        // Double также extends Number
        copy(doubles, numbers);
        System.out.println(numbers); // [1, 2, 3, 1.1, 2.2, 3.3]
    }
}
