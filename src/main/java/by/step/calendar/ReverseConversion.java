package by.step.calendar;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.Instant;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 🔁 LocalDateTime → Instant → Calendar
 * <p>
 * 💡 Альтернативно:
 * GregorianCalendar calendar = new GregorianCalendar();
 * calendar.setTimeInMillis(instant.toEpochMilli());
 * — но тогда теряется информация о часовом поясе.
 */
public class ReverseConversion {
    public static void main(String[] args) {
        // 1. LocalDateTime (без зоны!)
        LocalDateTime ldt = LocalDateTime.of(2025, 7, 4, 12, 0);

        // 2. Добавляем зону → Instant
        Instant instant = ldt.atZone(ZoneId.systemDefault()).toInstant();

        // 3. Instant → GregorianCalendar
        Calendar calendar = GregorianCalendar.from(
                ldt.atZone(ZoneId.systemDefault())
        );

        System.out.println("LocalDateTime: " + ldt);
        System.out.println("Instant: " + instant);
        System.out.println("Calendar: " + calendar.getTime());
    }
}