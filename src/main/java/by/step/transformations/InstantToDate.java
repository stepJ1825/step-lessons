package by.step.transformations;

import java.time.Instant;
import java.util.Date;

/**
 * 🔁 Instant → Date
 * 💡 Date всегда хранит момент времени в UTC (внутренне — миллисекунды с эпохи Unix), но при выводе использует локальную зону.
 */
public class InstantToDate {
    public static void main(String[] args) {
        Instant now = Instant.now();
        System.out.println("Instant: " + now);

        // Конвертация в Date
        Date date = Date.from(now);
        System.out.println("Date: " + date);
    }
}