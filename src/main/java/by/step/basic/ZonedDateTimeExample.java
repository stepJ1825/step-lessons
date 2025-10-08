package by.step.basic;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;

/**
 * 🌍 ZonedDateTime — дата и время с часовым поясом
 */
public class ZonedDateTimeExample {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("Текущее время с зоной: " + now);

        ZonedDateTime tokyo = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println("В Токио: " + tokyo);

        ZonedDateTime ny = ZonedDateTime.of(
            LocalDateTime.of(2025, 7, 4, 12, 0),
            ZoneId.of("America/New_York")
        );
        System.out.println("4 июля в Нью-Йорке: " + ny);
    }
}