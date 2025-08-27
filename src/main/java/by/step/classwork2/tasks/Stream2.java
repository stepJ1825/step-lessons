package by.step.classwork2.tasks;

import by.step.classwork2.model.Order2;

import java.util.Arrays;
import java.util.List;

public class Stream2 {
    /*
    Что будет результатом? (выбрать один)
a) 4, 0.0
b) 4, 190.0
c) 17, 0.0
d) 17, 190.0
     */
    public static void main(String[] args) {
        List<Order2> orders = Arrays.asList(
                new Order2(1, 50),
                new Order2(5, 70),
                new Order2(7, 70));
        Order2 order = orders.stream()
                .reduce(new Order2(4, 0), (p1, p2) -> new Order2(p1.getOrderId(),
                        (p1.getAmount() + p2.getAmount())));
        System.out.print(order);
    }
}

