package by.step.basic;

import java.time.LocalDate;

/**
 * 📆 LocalDate — только дата (без времени)
 */
public class LocalDateExample {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("Сегодня: " + today);

        LocalDate birthday = LocalDate.of(1990, 5, 15);
        System.out.println("День рождения: " + birthday);

        // Добавить/вычесть
        LocalDate nextWeek = today.plusWeeks(1);
        System.out.println("Через неделю: " + nextWeek);

        // Проверка дня недели
        System.out.println("День недели: " + today.getDayOfWeek());
    }
}