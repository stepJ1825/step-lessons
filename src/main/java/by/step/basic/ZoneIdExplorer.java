package by.step.basic;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ZoneIdExplorer {
    public static void main(String[] args) {
        Set<String> allZones = ZoneId.getAvailableZoneIds();
        LocalDateTime dt = LocalDateTime.now();

// Create a List using the set of zones and sort it.
        List<String> zoneList = new ArrayList<>(allZones);

        for (String zone : zoneList) {
            ZoneId zone1 = ZoneId.of(zone);
            ZonedDateTime zdt = dt.atZone(zone1);
            ZoneOffset offset = zdt.getOffset();
            String out = String.format("%35s %10s%n", zone, offset);
            System.out.printf(out);
        }
    }
}
