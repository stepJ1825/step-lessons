package by.step.classwork2.tasks;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream7 {

    public static void main(String[] args) {
//что выведет следующий код
        Stream.of("b", "a", "d", "c")
                .map(val -> val + 1)
                .peek(System.out::println)
                .forEach(System.out::println);


//что выведет следующий код
        Stream.of("b", "a", "d", "c")
                .map(val -> val + 1)
                .peek(System.out::println)
                .sorted()
                .forEach(System.out::println);

//что выведет следующий код
        Stream.of(0, 3, 0, 0, 5)
                .peek(x -> System.out.format("before distinct: %d%n", x))
                .distinct()
                .peek(x -> System.out.format("after distinct: %d%n", x))
                .map(x -> x * x)
                .forEach(x -> System.out.format("after map: %d%n", x));

//что выведет следующий код
        IntStream.range(0, 100_000)
                .parallel()
                .filter(x -> x % 10_000 == 0)
                .map(x -> x / 10_000)
                .forEach(System.out::println);

    }
}
