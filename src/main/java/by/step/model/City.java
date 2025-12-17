package by.step.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class City {
    private String name;
    private int foundationYear;
    private double square; // может быть null
    private List<People> peopleList; // массив длинной от 0 до 1_000_000

    @Data
    @Builder
    public static class People {
        private String fullname; // проверка регулярным выражением "Имя Фамилия"
        private Language language; // TODO: подумать как реализовать
    }

    public static enum Language {
        RUSSIAN, ENGLISH, BELARUSSIAN;
    }

}


//City.builder().name("Minsk").square(123456.123).foundationYear(987).peopleList(List.of(City.People.builder().fullname("Petr Petrov").language(City.Language.ENGLISH).build()))