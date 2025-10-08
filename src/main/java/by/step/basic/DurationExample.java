package by.step.basic;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * 🕰️ Duration — промежуток времени между двумя моментами (часы, минуты, секунды)
 */
public class DurationExample {
    public static void main(String[] args) {
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(17, 30);

        Duration workDay = Duration.between(start, end);
        System.out.println("Рабочий день: " + workDay); // PT8H30M
        System.out.println("В часах: " + workDay.toHours()); // 8
        System.out.println("В минутах: " + workDay.toMinutes()); // 510

        Duration durationOfDays = Duration.ofDays(2000);
        System.out.println(durationOfDays.toMinutes());
    }
}