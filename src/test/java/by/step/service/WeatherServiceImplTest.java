package by.step.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.List;

class WeatherServiceImplTest {

    WeatherServiceImpl weatherServiceImpl = new WeatherServiceImpl();

    @Test
    void someTest(){
        List<LocalDate> snowDates = weatherServiceImpl.findSnowDates(-5f);
        Assertions.assertThat(snowDates).isNotEmpty();
    }

    @Test
    void getWeathers() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<? extends WeatherServiceImpl> aClass = weatherServiceImpl.getClass();
        Method getWeathers = aClass.getDeclaredMethod("getWeathers");
        getWeathers.setAccessible(true);
        Object invoke = getWeathers.invoke(weatherServiceImpl);
        Assertions.assertThat(invoke).isNotNull();
    }

}