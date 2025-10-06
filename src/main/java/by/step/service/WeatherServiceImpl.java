package by.step.service;

import by.step.model.MyRegion;
import by.step.model.Precipitation;
import by.step.model.Weather;
import by.step.repository.WeatherRepository;
import by.step.repository.WeatherRepositoryImpl;

import java.time.LocalDate;
import java.util.List;

public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository repository = new WeatherRepositoryImpl();

    @Override
    public List<Weather> findByRegion(MyRegion myRegion) {
        return repository.findByRegion(myRegion);
    }

    @Override
    public List<LocalDate> findSnowDates(Float temperature) {
        List<Weather> allWeathers = getWeathers();
        return getList(temperature, allWeathers);
    }

    private static List<LocalDate> getList(Float temperature, List<Weather> allWeathers) {
        return allWeathers.stream()
                .filter(weather -> Precipitation.SNOW.equals(
                        weather.getPrecipitation()))
                .filter(weather -> weather.getTemperature() < temperature)
                .map(Weather::getDate)
                .toList();
    }

    private List<Weather> getWeathers() {
        return repository.getAllWeathers();
    }
}
