package by.step.classwork2.tasks;

import by.step.classwork2.model.Item;

import java.util.List;

public class Stream4 {
    /*
    Что будет результатом? (выбрать один)
a) compilation fails
b) 0.0
c) 15.0
d) 20.0
     */
    public static void main(String[] args) {
        List<Item> items = List.of(
                new Item("Jeans", 20),
                new Item("Socks", 10),
                new Item("Jacket", 30));
        var value = items.stream()
                .filter(s -> s.name().endsWith("s"))
                .mapToDouble(Item::price)
                .average()
                .getAsDouble();
        System.out.print(value);
    }
}

