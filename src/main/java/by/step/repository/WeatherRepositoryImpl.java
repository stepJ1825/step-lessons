package by.step.repository;

import by.step.model.MyRegion;
import by.step.model.Precipitation;
import by.step.model.Weather;

import java.time.LocalDate;
import java.util.List;

public class WeatherRepositoryImpl implements WeatherRepository {
    @Override
    public List<Weather> getAllWeathers() {
        return List.of(
                Weather.builder().temperature(-10f)
                        .date(LocalDate.now().minusMonths(2))
                        .precipitation(Precipitation.SNOW)
                        .build(),
                Weather.builder().temperature(-1f)
                        .date(LocalDate.now())
                        .precipitation(Precipitation.SNOW)
                        .build(),
                Weather.builder().temperature(+30f)
                        .date(LocalDate.now().minusYears(10))
                        .precipitation(Precipitation.RAIN)
                        .build());
    }

    @Override
    public Weather findById(int id) {
        return null;
    }

    @Override
    public List<Weather> findByRegion(MyRegion myRegion) {
        return List.of();
    }
}
