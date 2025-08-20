package by.step.classwork2;

import by.step.classwork2.model.Order2;

import java.util.Arrays;
import java.util.List;

public class Stream3 {
    /*
    Что будет результатом? (выбрать один)
a) ничего не будет выведено
b) 5, 70.0
c) 1, 50.0
d) 7, 70.0
     */
    public static void main(String[] args) {
        List<Order2> orders = Arrays.asList(
                new Order2(1, 50),
                new Order2(5, 70),
                new Order2(7, 70));
        orders.stream()
                .reduce((p1, p2) -> p1.getAmount() > p2.getAmount() ? p1 : p2)
                .ifPresent(System.out::println);
    }
}

