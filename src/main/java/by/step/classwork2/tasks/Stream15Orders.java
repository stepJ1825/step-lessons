package by.step.classwork2.tasks;

import by.step.classwork2.model.Order15;

import java.util.Arrays;
import java.util.List;

public class Stream15Orders {
    public static void main(String[] args) {
        List<Order15> orders = Arrays.asList(
                new Order15("2023-01", "CustomerA", 100),
                new Order15("2023-01", "CustomerB", 200),
                new Order15("2023-02", "CustomerA", 150),
                new Order15("2023-02", "CustomerC", 300)
        );

        // 1. Сгруппируйте заказы по месяцам в TreeMap
        // 2. Найдите общую сумму заказов для каждого месяца
        // 3. Для каждого месяца получите список уникальных клиентов

        // Для каждого месяца найдите:
        // 4. Общую сумму заказов в формате "$XХХХ.XX"
        // 5. Количество уникальных клиентов
        // 6. Самый крупный заказ (сумма)
    }
}
