package by.step.calendar;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 🔁 Calendar → ZonedDateTime
 * <p>
 * 💡 Метод toZonedDateTime() доступен только у GregorianCalendar, поэтому приведение к нему необходимо.
 */
public class CalendarToZonedDateTime {
    public static void main(String[] args) {
        // Создаём Calendar (например, GregorianCalendar)
        Calendar calendar = new GregorianCalendar(2025, Calendar.MARCH, 8, 14, 30, 0);
        System.out.println("Calendar: " + calendar.getTime());

        // Конвертация в ZonedDateTime
        ZonedDateTime zdt = ((GregorianCalendar) calendar).toZonedDateTime();
        System.out.println("ZonedDateTime: " + zdt);
    }
}