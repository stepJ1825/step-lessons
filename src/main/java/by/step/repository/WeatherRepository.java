package by.step.repository;

import by.step.model.MyRegion;
import by.step.model.Weather;

import java.util.List;

public interface WeatherRepository {
    List<Weather> getAllWeathers();

    Weather findById(int id);
//   Optional<Weather> findById(int id);

    List<Weather> findByRegion(MyRegion myRegion);

}
