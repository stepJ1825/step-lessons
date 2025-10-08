package by.step.calendar;

import java.time.Instant;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 🔁 Calendar → Instant
 * <p>
 * ✅ Работает только с GregorianCalendar. Если у вас Calendar, убедитесь, что он на самом деле GregorianCalendar.
 */
public class CalendarToInstant {
    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(2025, Calendar.MARCH, 8, 14, 30);
        System.out.println("Calendar: " + calendar.getTime());

        // Только GregorianCalendar поддерживает toInstant()
        Instant instant = ((GregorianCalendar) calendar).toInstant();
        System.out.println("Instant: " + instant);

        Calendar calendar1 = new GregorianCalendar();
        System.out.println(calendar1);
    }
}