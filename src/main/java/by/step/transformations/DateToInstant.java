package by.step.transformations;

import java.time.Instant;
import java.util.Date;

/**
 * 🔁 Date → Instant
 * 💡 Date всегда хранит момент времени в UTC (внутренне — миллисекунды с эпохи Unix), но при выводе использует локальную зону.
 */
public class DateToInstant {
    public static void main(String[] args) {
        Date oldDate = new Date(); // текущая дата по старому API
        System.out.println("Date: " + oldDate);

        // Конвертация в Instant
        Instant instant = oldDate.toInstant();
        System.out.println("Instant: " + instant);
    }
}