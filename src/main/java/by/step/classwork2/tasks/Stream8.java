package by.step.classwork2.tasks;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream8 {

    public static void main(String[] args) {
        // найти все числа в диапазоне от 0 до 1000 которые делятся без остатка на 3
        // и не делятся на 5, при этом сумма цифр числа должна быть меньше 10
        // Задачу выполнить с помощью Stream API
        IntStream.rangeClosed(0, 1000)
                .filter(value -> value % 3 == 0 && value % 5 != 0)
                .filter(value -> {
                    //18 -> 9 - true
                    //177 -> 15 - false
                    String stringValue = String.valueOf(value);
                    int sum = 0;
                    String[] split = stringValue.split("");
                    for (int i = 0; i < split.length; i++) {
                        sum += Integer.parseInt(split[i]);
                    }
                    return sum < 10;
                })
                .forEach(System.out::println);
    }
}
