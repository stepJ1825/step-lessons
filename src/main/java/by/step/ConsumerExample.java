package by.step;

import java.util.ArrayList;
import java.util.List;

/**
 * Пример 3: Добавление элементов
 */
public class ConsumerExample {

    // Consumer Super - только записываем в destination
    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        List<Object> objects = new ArrayList<>();
        List<Number> numbers = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();

        addNumbers(objects);  // Object super Integer
        addNumbers(numbers);  // Number super Integer
        addNumbers(integers); // Integer super Integer

        System.out.println("Objects: " + objects); // [1, 2, 3, 4, 5]
        System.out.println("Numbers: " + numbers); // [1, 2, 3, 4, 5]
        System.out.println("Integers: " + integers); // [1, 2, 3, 4, 5]
    }
}