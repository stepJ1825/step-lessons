package by.step.calendar;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;

/**
 * 🔁 ZonedDateTime → Calendar
 * <p>
 * 💡 Используется статический метод GregorianCalendar.from(ZonedDateTime).
 */
public class ZonedDateTimeToCalendar {
    public static void main(String[] args) {
        ZonedDateTime zdt = ZonedDateTime.of(2025, 3, 8, 14, 30, 0, 0, ZoneId.of("Europe/Moscow"));
        System.out.println("ZonedDateTime: " + zdt);

        // Конвертация в GregorianCalendar
        GregorianCalendar calendar = GregorianCalendar.from(zdt);
        System.out.println("Calendar time: " + calendar.getTime());
        System.out.println("Calendar zone: " + calendar.getTimeZone().getID());
    }
}