package by.step.basic;

import java.time.Instant;

/**
 * 🌐 Instant — момент времени в UTC (Unix timestamp)
 */
public class InstantExample {
    public static void main(String[] args) {
        Instant now = Instant.now();
        System.out.println("Текущий момент (UTC): " + now);

        // Из миллисекунд
        Instant fromMillis = Instant.ofEpochMilli(System.currentTimeMillis());
        System.out.println("Из миллисекунд: " + fromMillis);

        // Добавить 10 секунд
        Instant future = now.plusSeconds(10);
        System.out.println("Через 10 секунд: " + future);
    }
}