package by.step.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDTO {
    private int id;
    private String title;
    private String authorFullName;
    private String genreName;
    private int releaseYear;
    private float rating;
    private String displayInfo; // вычисляемое поле
}

