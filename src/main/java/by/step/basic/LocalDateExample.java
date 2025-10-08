package by.step.basic;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * 📆 LocalDate — только дата (без времени)
 */
public class LocalDateExample {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("Сегодня: " + today);

        LocalDate birthday = LocalDate.of(1990, 5, 15);
        LocalDate birthday2 = LocalDate.of(1990, Month.JANUARY, 15);
        System.out.println("День рождения: " + birthday);

        // Добавить/вычесть
        LocalDate nextWeek = today.plusWeeks(1);
        System.out.println("Через неделю: " + nextWeek);
        LocalDate minusWeek = nextWeek.minus(1, ChronoUnit.WEEKS);
        System.out.println("Вернули обратно неделю: " + minusWeek);


        // Проверка дня недели
        System.out.println("День недели: " + today.getDayOfWeek());
    }
}