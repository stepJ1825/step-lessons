package by.step.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookFullDto {
    private Long id;
    private String title;
    private String author;
    private Integer year;
    private Boolean available;
    private Double rating;
    private String isbn; // может быть null
    private List<String> tags;

    private Publisher publisher;

    private List<Translation> translations;

    private Metadata metadata;

    // Вложенный DTO: Издательство
    @Data
    public static class Publisher {
        private String name;
        private String country;
        private Integer founded;
        private Boolean active;
    }

    // Вложенный DTO: Перевод
    @Data
    public static class Translation {
        private String language;
        private String title;
        private String translator;
    }

    // Вложенный DTO: Метаданные
    @Data
    public static class Metadata {
        private Integer pages;
        private List<Double> dimensions; // [ширина, высота, толщина]
        private Boolean hasIllustrations;
        private List<String> reviews; // пустой массив
    }
}


/*
{
  "id": 42,
  "title": "Мастер и Маргарита",
  "author": "Михаил Булгаков",
  "year": 1967,
  "available": true,
  "rating": 4.9,
  "isbn": null,

  "tags": ["классика", "фэнтези", "сатира", "философия"],

  "publisher": {
    "name": "АСТ",
    "country": "Россия",
    "founded": 1990,
    "active": true
  },

  "translations": [
    {
      "language": "English",
      "title": "The Master and Margarita",
      "translator": "Richard Pevear"
    },
    {
      "language": "Français",
      "title": "Le Maître et Marguerite",
      "translator": "Serguei Chotkine"
    }
  ],

  "metadata": {
    "pages": 480,
    "dimensions": [14.5, 21.0, 2.8],
    "hasIllustrations": false,
    "reviews": []
  }
}
 */