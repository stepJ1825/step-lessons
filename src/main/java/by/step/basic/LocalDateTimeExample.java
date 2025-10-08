package by.step.basic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 📅 LocalDateTime — дата и время без часового пояса
 */
public class LocalDateTimeExample {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now(); // текущие дата и время
        System.out.println("Сейчас: " + now);

        LocalDateTime specific = LocalDateTime.of(2025, 12, 25, 15, 30, 45);
        System.out.println("Заданное время: " + specific);

        // Форматирование
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String formatted = now.format(formatter);
        System.out.println("Форматировано: " + formatted);

        // Парсинг
        LocalDateTime parsed = LocalDateTime.parse("2025-12-25T15:30:45");
        System.out.println("Разобралось: " + parsed);
    }
}