package by.step.model.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class BookJPA {
    //id, title, author, genre, year, rating
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1. Важно для SERIAL в Postgres
    private int id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorJPA author;
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreJPA genre;
    private int year;
    private float rating;
}
