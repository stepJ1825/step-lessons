package by.step.transformations;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 🔁 LocalDateTime → Date
 * 💡 Date всегда хранит момент времени в UTC (внутренне — миллисекунды с эпохи Unix), но при выводе использует локальную зону.
 */
public class LocalDateTimeToDate {
    public static void main(String[] args) {
        LocalDateTime ldt = LocalDateTime.now();

        // Сначала в ZonedDateTime (добавляем зону), потом в Instant, потом в Date
        Date date = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());


        System.out.println("LocalDateTime: " + ldt);
        System.out.println("Date: " + date);
    }
}