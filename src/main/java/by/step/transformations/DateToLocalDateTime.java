package by.step.transformations;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 🔁 Date → LocalDateTime (без часового пояса)
 * 💡 Date всегда хранит момент времени в UTC (внутренне — миллисекунды с эпохи Unix), но при выводе использует локальную зону.
 */
public class DateToLocalDateTime {
    public static void main(String[] args) {
        Date date = new Date();

        // Конвертируем через Instant и системную зону
        LocalDateTime localDateTime = LocalDateTime.ofInstant(
            date.toInstant(),
            ZoneId.systemDefault()
        );

        System.out.println("Date: " + date);
        System.out.println("LocalDateTime: " + localDateTime);
    }
}