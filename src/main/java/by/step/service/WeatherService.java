package by.step.service;

import by.step.model.MyRegion;
import by.step.model.Weather;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface WeatherService {
    /**
     * • Вывести сведения о погоде в заданном регионе.
     *
     * @param myRegion
     * @return
     */
    List<Weather> findByRegion(MyRegion myRegion);

    /**
     * Вывести даты, когда в заданном регионе шел снег и температура была ниже
     * заданной отрицательной
     *
     * @param temperature - температура воздуха (например -5)
     * @return
     */
    List<LocalDate> findSnowDates(Float temperature);
}
