package by.step;

import java.util.stream.Stream;

public class Stream3 {
    public static void main(String[] args) {
        Stream.iterate(2, x -> x < 25, x -> x + 6)
                .limit(100000)
                .forEach(System.out::println);
        // 2, 8, 14, 20
    }
}
