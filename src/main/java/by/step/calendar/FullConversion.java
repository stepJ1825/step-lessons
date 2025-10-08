package by.step.calendar;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 🔁 Calendar → Instant → LocalDateTime
 */
public class FullConversion {
    public static void main(String[] args) {
        // 1. Создаём Calendar
        Calendar cal = new GregorianCalendar(2025, Calendar.JULY, 4, 12, 0, 0);

        // 2. Calendar → Instant
        Instant instant = ((GregorianCalendar) cal).toInstant();

        // 3. Instant → LocalDateTime (в системной зоне)
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        System.out.println("Calendar time: " + cal.getTime());
        System.out.println("Instant: " + instant);
        System.out.println("LocalDateTime: " + ldt);
    }
}