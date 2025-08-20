package by.step.classwork2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private String type;
    private String category;
    private double price;
    private int quantity;

    public Product(String name, String category, double price) {
        this.type = name;
        this.category = category;
        this.price = price;
    }
}
