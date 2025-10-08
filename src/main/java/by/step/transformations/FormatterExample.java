package by.step.transformations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * 📝 DateTimeFormatter — форматирование и парсинг
 */
public class FormatterExample {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2025, 3, 8);

        // Встроенные форматы
        System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // 2025-03-08
        System.out.println(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))); // 08/03/2025

        // Парсинг
        try {
            LocalDate parsed = LocalDate.parse("25.12.2025", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            System.out.println("Разобралась: " + parsed);
        } catch (DateTimeParseException e) {
            System.err.println("Ошибка парсинга: " + e.getMessage());
        }
    }
}