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

        LocalDateTime specific = LocalDateTime.of(2025, 10, 8, 19, 10, 45);
        System.out.println("Заданное время: " + specific);

        // Форматирование
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String formatted = now.format(formatter);
        System.out.println("Форматировано: " + formatted);

        // Парсинг
        LocalDateTime parsed = LocalDateTime.parse("08.10.2025 19:16:19", formatter);
        System.out.println("Разобралось: " + parsed);
    }
}