package by.step.functionalinterfaces;

import java.time.LocalDateTime;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * Predicate<T> UnaryOperator<T> BinaryOperator<T> Consumer<T> Function<T,R> Supplier<T>
 */
public class App {
    public static void main(String[] args) throws InterruptedException {

        //Проверяет соблюдение некоторого условия для объекта T
        Predicate<Integer> isPositive = x -> x > 0;
        System.out.println(isPositive.test(5)); // true
        System.out.println(isPositive.test(-7)); // false
        Stream<Integer> stream = Stream.of(5, 7, 10, -10, -20);
        long count = stream
                .filter(isPositive)
                .filter(integer -> integer % 2 == 0)
                .count();
        System.out.println(count);


        //принимает в качестве параметра объект типа T, выполняет над ними операции и возвращает результат операций в виде объекта типа T
        UnaryOperator<Integer> square = x -> x * x;
        System.out.println(square.apply(5)); // 25

        //принимает в качестве параметра два объекта типа T, выполняет над ними бинарную операцию
        //и возвращает ее результат также в виде объекта типа T
        BinaryOperator<Integer> multiply = (x, y) -> x * y;
        System.out.println(multiply.apply(3, 5)); // 15
        System.out.println(multiply.apply(10, -2)); // -20

        //представляет функцию перехода от объекта типа T к объекту типа R
        Function<Integer, String> convert = x -> x + " долларов";
        System.out.println(convert.apply(5)); // 5 долларов

        //выполняет некоторое действие над объектом типа T, при этом ничего не возвращая
        Consumer<Integer> printer = x -> System.out.printf("%d долларов \n", x);
        Consumer<String> printer2 = System.out::println;
        printer.accept(600); // 600 долларов
        printer2.accept("some string");

        //не принимает никаких аргументов, но должен возвращать объект типа T
        Supplier<MyObject> myObjectFactory = () -> new MyObject(LocalDateTime.now());
        MyObject myObject1 = myObjectFactory.get();
        MyObject myObject2 = myObjectFactory.get();
        System.out.println(myObject1.getCreatedAt());
        System.out.println(myObject2.getCreatedAt());
    }
}

