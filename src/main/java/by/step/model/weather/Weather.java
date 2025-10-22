package by.step.model.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class Weather {
    private int id;
    private MyRegion myRegion;
    private LocalDate date;
    private Float temperature;

    private Precipitation precipitation;
    //  id   precipitation
    //  1    RAIN,
    //  2    SNOW,
    //  3    NO
}
