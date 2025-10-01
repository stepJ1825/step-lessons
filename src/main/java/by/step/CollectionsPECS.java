package by.step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Пример 6: Аналогия с методами Collections
 */
public class CollectionsPECS {

    // Аналогично Collections.addAll()
    public static <T> void addAllToCollection(
            Collection<? super T> target, // Consumer - принимает T и его супертипы
            Collection<? extends T> source // Producer - предоставляет T и его подтипы
    ) {
        target.addAll(source);
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3);

        Collection<Number> numbers = new ArrayList<>();

        // Добавляем Integer (подтип Number)
        addAllToCollection(numbers, integers);

        // Добавляем Double (подтип Number)
        addAllToCollection(numbers, doubles);

        System.out.println("All numbers: " + numbers);
        // [1, 2, 3, 1.1, 2.2, 3.3]
    }
}
