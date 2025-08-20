package by.step.classwork2;

import by.step.classwork2.model.Product;

import java.util.Arrays;
import java.util.List;

public class Stream13Products {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200),
                new Product("Phone", "Electronics", 800),
                new Product("Book", "Education", 20),
                new Product("Pen", "Education", 2),
                new Product("Laptop", "Electronics", 2000),
                new Product("Phone", "Electronics", 250),
                new Product("Chair", "Furniture", 150)
        );

        // 1. Сгруппируйте товары по категориям
        // 2. Найдите самый дорогой товар в каждой категории
        // 3. Посчитайте общую стоимость товаров в каждой категории

        // 4. Общий объем продаж с форматированием для каждого типа (type)
        // 5. Категорию с максимальными продажами
        // 6. Процент от общего объема продаж для каждого типа (type)


    }
}
