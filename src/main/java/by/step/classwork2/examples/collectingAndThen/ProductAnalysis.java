package by.step.classwork2.examples.collectingAndThen;

import by.step.classwork2.model.Product;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductAnalysis {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200, 5),
                new Product("Phone", "Electronics", 800, 10),
                new Product("Book", "Education", 20, 100),
                new Product("Pen", "Education", 2, 500),
                new Product("Chair", "Furniture", 150, 15)
        );

        // Общая стоимость товаров по категориям с форматированием
        Map<String, String> totalValueByCategory = products.stream()
                                                           .collect(Collectors.groupingBy(
                                                                   Product::getCategory,
                                                                   Collectors.collectingAndThen(
                                                                           Collectors.summingDouble(p -> p.getPrice() * p.getQuantity()),
                                                                           total -> String.format("$%.2f", total) // Форматирование
                                                                   )
                                                           ));
        System.out.println("Общая стоимость: " + totalValueByCategory);

        // Самый дорогой товар в категории (имя товара)
        Map<String, String> mostExpensiveByCategory = products.stream()
                                                              .collect(Collectors.groupingBy(
                                                                      Product::getCategory,
                                                                      Collectors.collectingAndThen(
                                                                              Collectors.maxBy(Comparator.comparing(Product::getPrice)),
                                                                              optional -> optional.map(Product::getType).orElse("Нет товаров")
                                                                      )
                                                              ));
        System.out.println("Самый дорогой товар: " + mostExpensiveByCategory);
    }
}
