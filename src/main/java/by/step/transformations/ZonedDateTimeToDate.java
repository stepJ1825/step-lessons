package by.step.transformations;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * 🔁 ZonedDateTime → Date
 * 💡 Date всегда хранит момент времени в UTC (внутренне — миллисекунды с эпохи Unix), но при выводе использует локальную зону.
 */
public class ZonedDateTimeToDate {
    public static void main(String[] args) {
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));

        Date date = Date.from(zdt.toInstant());
        System.out.println("ZonedDateTime (Tokyo): " + zdt);
        System.out.println("Date (UTC-based): " + date);
    }
}