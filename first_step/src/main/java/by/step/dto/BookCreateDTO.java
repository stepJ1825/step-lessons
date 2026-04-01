package by.step.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateDTO {
    private String title;
    private int authorId;
    private int genreId;
    private int releaseYear;
    private float rating;
}
