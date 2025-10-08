package by.step.basic;

import java.time.LocalTime;

/**
 * ⏰ LocalTime — только время (без даты)
 */
public class LocalTimeExample {
    public static void main(String[] args) {
        LocalTime now = LocalTime.now();
        System.out.println("Текущее время: " + now);
        now.plusHours(10);

        LocalTime lunch = LocalTime.of(13, 0, 0);
        System.out.println("Обед: " + lunch);

        LocalTime later = lunch.plusHours(2).plusMinutes(30);
        System.out.println("Позже: " + later);
    }
}