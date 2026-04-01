package by.step.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookFullDto {
    private int id;
    private String title;
    private AuthorFullDto author;
    private GenreFullDto genre;
    private int releaseYear;
    private float rating;
}
