package by.step.classwork2.model;

import lombok.Data;

@Data
public class Order2 {
    private long orderId;
    private double amount;

    public Order2(long orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
    }

    public String toString() {
        return orderId + ", " + amount;
    }
}
