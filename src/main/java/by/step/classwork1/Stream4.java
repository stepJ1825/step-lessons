package by.step.classwork1;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class Stream4 {
    public static void main(String[] args) {
        Predicate<Integer> positivePredicate = x -> x > 0;
        Predicate<Integer> evenPredicate = x -> x % 2 == 0;
        Stream.of(-1, -6, -11, 2, 3, 10)
                .filter(positivePredicate.and(evenPredicate.negate()))
                .forEach(System.out::println);
        // Вывода нет, так как после

        Stream.of(-1, -6, -11, 2, 3, 10)
                .map(i -> i + "number")
                .forEach(System.out::println);
    }
}
