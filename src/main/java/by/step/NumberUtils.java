package by.step;

import java.util.*;

/**
 * Пример 2: Сумма чисел
 */
public class NumberUtils {

    // Producer Extends - только читаем числа
    public static double sum(List<? extends Number> numbers) {
        double total = 0.0;
        for (int i = 0; i < numbers.size(); i++) {
            Number number = numbers.get(i);
            total += number.doubleValue();
        }
        return total;
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<Double> doubles = Arrays.asList(1.5, 2.5, 3.5);

        System.out.println("Sum integers: " + sum(integers)); // 6.0
        System.out.println("Sum doubles: " + sum(doubles));  // 7.5

        // Смешанный список
        List<Number> mixed = Arrays.asList(1, 2.5, 3L);
        System.out.println("Sum mixed: " + sum(mixed)); // 6.5
    }
}
