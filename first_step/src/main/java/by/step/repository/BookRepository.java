package by.step.repository;

import by.step.dto.BookDetailsDTO;
import by.step.entity.Author;
import by.step.entity.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    //-----------------------------------------
    // SPRING DATA JPA METHODS
    List<Book> findByAuthor(Author author);

    List<Book> findByAuthorSurname(String surname);

    List<Book> findByReleaseYearBetween(int start, int end);

    List<Book> findByAuthorNotNull();

    List<Book> findByAuthorIsNull();

    List<Book> findByAuthorFirstNameContains(String firstName);

    //-----------------------------------------
    // NATIVE SQL QUERIES
    @Query(nativeQuery = true, value = "SELECT * FROM books;")
    List<Book> findAllNative();

    @Query(value = "SELECT * FROM books b WHERE b.release_year > :release_year ORDER BY b.rating DESC",
           nativeQuery = true)
    List<Book> findTopRatedAfterYear(@Param("release_year") int release_year);

    @Query(value = """
            SELECT b.*, a.first_name, a.surname, g.name as genre_name 
            FROM books b
            JOIN authors a ON b.author_id = a.id
            JOIN genres g ON b.genre_id = g.id
            WHERE b.rating >= :minRating
            """, nativeQuery = true)
    List<Object[]> findBookDetailsWithJoins(@Param("minRating") float minRating);

    @Query(name = "Book.findDetailsByReleaseYear", nativeQuery = true)
    List<BookDetailsDTO> findBookDetailsByYear(@Param("release_year") int release_year);

    //-----------------------------------------
    // HQL QUERIES
    @Query("SELECT b FROM Book b WHERE upper(b.title) LIKE CONCAT('%', upper(:keyword), '%')")
    List<Book> searchByTitleKeyword(@Param("keyword") String keyword);

    @Query("SELECT b FROM Book b JOIN b.author a WHERE a.surname = :surname")
    List<Book> findByAuthorSurnameHQL(@Param("surname") String surname);

    @Query("SELECT b FROM Book b WHERE b.rating >= :minRating AND b.genre.name = :genreName")
    List<Book> findByRatingAndGenre(
            @Param("minRating") float minRating,
            @Param("genreName") String genreName
    );

    //-----------------------------------------
    // NAMED QUERIES
    @Query(name = "Book.findByTitle")
    List<Book> findByTitle(@Param("title") String title); // автоматически свяжется с @NamedQuery("Book.findByTitle")

    @Query(name = "Book.findByAuthorAndReleaseYearRange")
    List<Book> findBooksByAuthorAndPeriod(
            @Param("authorId") Integer authorId,
            @Param("from") int from,
            @Param("to") int to
    );

    //-----------------------------------------
    // EntityGraph QUERIES
    @EntityGraph(attributePaths = {"author", "genre"})
    @Query("SELECT b FROM Book b WHERE b.author.id = :authorId")
    List<Book> findByAuthorIdWithGraph(@Param("authorId") Integer authorId);


    Boolean existsByTitle(String title);


}
