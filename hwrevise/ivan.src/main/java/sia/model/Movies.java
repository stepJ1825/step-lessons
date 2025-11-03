package sia.model;

import lombok.*;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movies {
    private int id;
    private String title;
    private List<Actor> actors;
    private List<Director> directors;
    private int releaseYear;
    private String country;
}
