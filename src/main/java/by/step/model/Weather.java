package by.step.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class Weather {
    private MyRegion myRegion;
    private LocalDate date;
    private Float temperature;
    private Precipitation precipitation;
}
