package by.step.transformations;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * 🔁 Date → ZonedDateTime (с часовым поясом)
 * 💡 Date всегда хранит момент времени в UTC (внутренне — миллисекунды с эпохи Unix), но при выводе использует локальную зону.
 */
public class DateToZonedDateTime {
    public static void main(String[] args) {
        Date date = new Date();

        ZonedDateTime zdt = date.toInstant().atZone(ZoneId.of("Europe/Moscow"));
        System.out.println("Date: " + date);
        System.out.println("ZonedDateTime (Moscow): " + zdt);
    }
}