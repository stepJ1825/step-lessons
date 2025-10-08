package by.step.basic;

import java.time.ZoneId;
import java.util.Set;

/**
 * 🧭 ZoneId — работа с часовыми поясами
 */
public class ZoneIdExample {
    public static void main(String[] args) {
        // Текущая зона
        ZoneId systemZone = ZoneId.systemDefault();
        System.out.println("Системная зона: " + systemZone);

        // Конкретная зона
        ZoneId moscow = ZoneId.of("Europe/Moscow");
        System.out.println("Москва: " + moscow);

        // Все доступные зоны
        Set<String> zones = ZoneId.getAvailableZoneIds();
        System.out.println("Всего зон: " + zones.size());
        // System.out.println(zones); // осторожно — очень много!
    }
}