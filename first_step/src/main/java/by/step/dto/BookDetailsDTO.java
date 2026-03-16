package by.step.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailsDTO {
    private Integer id;
    private String title;
    private String authorName;    // first_name + surname из SQL
    private String genreName;     // name из genres
    private Float rating;

    // Опционально: factory-метод для удобного создания
    public static BookDetailsDTO of(Integer id, String title,
            String firstName, String surname,
            String genreName, Float rating) {
        return new BookDetailsDTO(
                id,
                title,
                firstName + " " + surname,  // склеиваем имя и фамилию
                genreName,
                rating
        );
    }
}