package by.step.transformations;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 🔁 Instant → LocalDateTime
 * <p>
 * ⚠️ LocalDateTime не содержит часового пояса, поэтому обязательно нужно указать зону, например ZoneId.systemDefault()
 * или ZoneId.of("UTC").
 */
public class InstantToLocalDateTime {
    public static void main(String[] args) {
        Instant instant = Instant.now();
        System.out.println("Instant (UTC): " + instant);

        // Преобразуем в LocalDateTime с учётом часового пояса
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        System.out.println("LocalDateTime (system zone): " + ldt);
    }
}