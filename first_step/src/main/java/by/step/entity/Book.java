package by.step.entity;

import by.step.dto.BookDetailsDTO;
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
@SqlResultSetMapping(
        name = "BookAuthorGenreMapping",
        classes = @ConstructorResult(
                targetClass = BookDetailsDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "title", type = String.class),
                        @ColumnResult(name = "author_name", type = String.class),
                        @ColumnResult(name = "genre_name", type = String.class),
                        @ColumnResult(name = "rating", type = Float.class)
                }
        )
)
@NamedNativeQuery(  // Опционально: именованный native-запрос
                    name = "Book.findDetailsByReleaseYear",
                    query = """
                            SELECT b.id, b.title, 
                                   a.first_name || ' ' || a.surname as author_name,
                                   g.name as genre_name,
                                   b.rating
                            FROM books b
                            JOIN authors a ON b.author_id = a.id
                            JOIN genres g ON b.genre_id = g.id
                            WHERE b.release_year = :release_year
                            """,
                    resultSetMapping = "BookAuthorGenreMapping"
)
@NamedQueries({
        @NamedQuery(
                name = "Book.findByAuthorAndReleaseYearRange",
                query = "SELECT b FROM Book b WHERE b.author.id = :authorId AND b.releaseYear BETWEEN :from AND :to"
        ),
        @NamedQuery(
                name = "Book.findByTitle",
                query = "SELECT b FROM Book b WHERE b.title = :title"
        )
})
public class Book {
    //id, title, author, genre, year, rating
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1. Важно для SERIAL в Postgres
    private int id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;
    @Column(name = "release_year")
    private int releaseYear;
    private float rating;
}
