package by.step.basic;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * 📅 Period — промежуток между двумя датами (годы, месяцы, дни)
 */
public class PeriodExample {
    public static void main(String[] args) {
        LocalDate birth = LocalDate.of(1990, 5, 15);
        LocalDate today = LocalDate.now();

        Period age = Period.between(birth, today);
        System.out.printf("Возраст: %s%n", age); // P34Y2M10D
        System.out.printf("Лет: %d, месяцев: %d, дней: %d%n",
            age.getYears(), age.getMonths(), age.getDays());
    }
}