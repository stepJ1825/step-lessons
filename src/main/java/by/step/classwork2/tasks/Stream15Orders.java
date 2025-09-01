package by.step.classwork2.tasks;

import by.step.classwork2.model.Order15;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Stream15Orders {
    public static void main(String[] args) {
        List<Order15> orders = Arrays.asList(
                new Order15("2023-JAN", "CustomerA", 100),
                new Order15("2023-JAN", "CustomerB", 200),
                new Order15("2023-FEB", "CustomerA", 150),
                new Order15("2023-APR", "CustomerA", 150),
                new Order15("2023-MAR", "CustomerA", 150),
                new Order15("2023-MAR", "CustomerA", 250),
                new Order15("2023-FEB", "CustomerC", 300)
        );

        // 1. Сгруппируйте заказы по месяцам в TreeMap
        Map<String, List<Order15>> collect = orders.stream()
                .collect(Collectors.groupingBy(order ->
                                order.getOrderDate().split("-")[1],
                        TreeMap::new,  // Отсортированная Map по ключам
                        Collectors.toList()));

        // 2. Найдите общую сумму заказов для каждого месяца
        Map<String, Integer> collect2 = orders.stream()
                .collect(Collectors.groupingBy(order ->
                                order.getOrderDate().split("-")[1],
                                Collectors.summingInt(Order15::getCost)));

        // 3. Для каждого месяца получите список уникальных клиентов
        Map<String, List<String>> collectedDistinctCustomers = orders.stream()
                .collect(Collectors.groupingBy(order ->
                                order.getOrderDate().split("-")[1],
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .map(Order15::getCustomer)
                                        .distinct()
                                        .toList())
                ));

        // Для каждого месяца найдите:
        // 4. Общую сумму заказов в формате "$XХХХ.XX"
        // 5. Количество уникальных клиентов
        // 6. Самый крупный заказ (сумма)
    }
}
