package by.step.classwork2.tasks;

import by.step.classwork2.model.Order;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stream1 {
    /*
Что будет результатом? (выбрать один)
a) 70.0 50.0
b) 50.0 70.0 70.0
c) 1, 50.0 5, 70.0 7, 50.0
d) 5, 70.0 7, 50.0
e) ничего не будет выведено
     */
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order(1, 50),
                new Order(5, 70),
                new Order(7, 70));
        Map<Double, List<Order>> collect = orders.stream()
                .collect(Collectors.groupingBy(Order::amount));
        collect.forEach((source, r) -> System.out.print(source + " "));
    }

}

