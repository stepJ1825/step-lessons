package by.step.classwork1;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream9Boxed {
    public static void main(String[] args) {
        IntStream.rangeClosed(0, 10) //ещё примитивы
                .boxed() //уже объекты
                .map(Object::getClass)
                .forEach(System.out::println);

        Stream.of(1,2,3,4,5)
                .mapToInt(value -> value)
                .map(operand -> operand)
                .boxed()
                .map(integer -> integer);

    }
}
