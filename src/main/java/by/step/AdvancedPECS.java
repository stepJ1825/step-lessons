package by.step;

import java.util.*;
import java.util.function.Predicate;

/**
 * Пример 5: Продвинутое копирование с фильтрацией
 */
public class AdvancedPECS {

    // Producer Extends + Consumer Super
    public static <T> void copyIf(
            List<? extends T> source,    // Producer - читаем
            List<? super T> destination, // Consumer - записываем
            Predicate<? super T> filter  // Consumer - принимает любой супертип T
    ) {
        for (T item : source) {
            if (filter.test(item)) {
                destination.add(item);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Number> evenNumbers = new ArrayList<>();
        List<Number> largeNumbers = new ArrayList<>();

        // Фильтр для четных чисел
        copyIf(numbers, evenNumbers, n -> n % 2 == 0);

        // Фильтр для больших чисел (Predicate<Number> работает с Integer)
        Predicate<Number> largePredicate = n -> n.doubleValue() > 5;
        copyIf(numbers, largeNumbers, largePredicate);

        System.out.println("Even numbers: " + evenNumbers); // [2, 4, 6, 8, 10]
        System.out.println("Large numbers: " + largeNumbers); // [6, 7, 8, 9, 10]
    }
}
