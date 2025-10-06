package by.step.controller;

import by.step.service.WeatherService;
import by.step.service.WeatherServiceImpl;

import java.time.LocalDate;
import java.util.List;

public class WeatherController {
    private final WeatherService weatherService = new WeatherServiceImpl();

    public void start() {
        List<LocalDate> snowDates = weatherService.findSnowDates(-5f);
        System.out.println(snowDates);
    }
}
