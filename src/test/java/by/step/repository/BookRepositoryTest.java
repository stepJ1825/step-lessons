package by.step.repository;

import by.step.ApplicationRunner;
import by.step.dto.BookDetailsDTO;
import by.step.entity.Author;
import by.step.entity.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Comparator;
import java.util.List;

@SpringBootTest //(classes = ApplicationRunner.class)
@ActiveProfiles("test")
@Sql(scripts = {
        "/sql/V3.0.1__Create_tables.sql",
        "/sql/V3.0.2__Insert_authors.sql",
        "/sql/V3.0.3__Insert_genres.sql",
        "/sql/V3.0.4__Insert_books.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {
        "classpath:sql/cleanup.sql"
}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test
    void checkFindAll() {
        List<Book> all = repository.findAll();
        Assertions.assertThat(all).isNotNull();
    }

    @Test
    void checkFindByAuthor() {
        Author author = Author.builder().id(102)
                .firstName("Michael")
                .surname("Brown")
                .build();
        List<Book> booksByAuthor = repository.findByAuthor(author);
        Assertions.assertThat(booksByAuthor).isNotEmpty();
    }

    @Test
    void checkFindByAuthorSurname() {
        List<Book> books = repository.findByAuthorSurname("Brown");
        Assertions.assertThat(books)
                .isNotEmpty()
                .extracting(book -> book.getAuthor().getSurname())
                .containsOnly("Brown");
    }

    @Test
    void checkFindByYearBetween() {
        List<Book> books = repository.findByReleaseYearBetween(2019, 2020);
        Assertions.assertThat(books)
                .isNotEmpty()
                .allMatch(book -> book.getReleaseYear() >= 2019 && book.getReleaseYear() <= 2020);
    }

    @Test
    void checkFindByAuthorNotNull() {
        List<Book> byAuthorNotNull = repository.findByAuthorNotNull();
        Assertions.assertThat(byAuthorNotNull).isNotEmpty();
    }

    @Test
    void checkFindByAuthorIsNull() {
        List<Book> byAuthorNotNull = repository.findByAuthorIsNull();
        Assertions.assertThat(byAuthorNotNull).isEmpty();
    }

    @Test
    void checkFindByAuthorNameContains() {
        List<Book> booksByAuthor = repository.findByAuthorFirstNameContains("Michael");
        Assertions.assertThat(booksByAuthor)
                .isNotEmpty()
                .allMatch(book -> book.getAuthor().getFirstName().contains("Michael"));
    }

    @Test
    void checkFindAllNative() {
        List<Book> all = repository.findAllNative();
        Assertions.assertThat(all).isNotNull();
    }

    @Test
    void checkFindTopRatedAfterYear() {
        int year = 2022;
        List<Book> books = repository.findTopRatedAfterYear(year);

        Assertions.assertThat(books)
                .isNotEmpty()
                .allMatch(book -> book.getReleaseYear() > year);

        Assertions.assertThat(books)
                .extracting(Book::getRating)
                .isSortedAccordingTo((Comparator<Float>) (a, b) -> Float.compare(b, a));
    }

    @Test
    void checkFindBookDetailsWithJoins() {
        float minRating = 4.8f;
        List<Object[]> rows = repository.findBookDetailsWithJoins(minRating);

        Assertions.assertThat(rows).isNotEmpty();
        for (Object[] row : rows) {
            Assertions.assertThat(row.length).isGreaterThanOrEqualTo(8);
            Float rating = ((Number) row[3]).floatValue();
            Assertions.assertThat(rating).isGreaterThanOrEqualTo(minRating);
        }
    }

    @Test
    void checkFindBookDetailsByYear() {
        int year = 2020;
        List<BookDetailsDTO> details = repository.findBookDetailsByYear(year);

        Assertions.assertThat(details)
                .isNotEmpty()
                .allSatisfy(dto -> {
                    Assertions.assertThat(dto.getId()).isNotNull();
                    Assertions.assertThat(dto.getTitle()).isNotBlank();
                    Assertions.assertThat(dto.getGenreName()).isNotBlank();
                });
    }

    @Test
    void checkSearchByTitleKeyword() {
        String keyword = "Ocean";
        List<Book> books = repository.searchByTitleKeyword(keyword);
        List<Book> booksByLowerCase = repository.searchByTitleKeyword(keyword.toLowerCase());

        Assertions.assertThat(books)
                .isNotEmpty()
                .allMatch(book -> book.getTitle().contains(keyword));

        Assertions.assertThat(booksByLowerCase)
                  .isNotEmpty()
                  .allMatch(book -> book.getTitle().contains(keyword));
    }

    @Test
    void checkFindByAuthorSurnameHql() {
        List<Book> books = repository.findByAuthorSurnameHQL("Brown");
        Assertions.assertThat(books)
                .isNotEmpty()
                .extracting(book -> book.getAuthor().getSurname())
                .containsOnly("Brown");
    }

    @Test
    void checkFindByRatingAndGenre() {
        float minRating = 4.8f;
        String genreName = "SCIENCE_FICTION";

        List<Book> books = repository.findByRatingAndGenre(minRating, genreName);
        Assertions.assertThat(books)
                .isNotEmpty()
                .allSatisfy(book -> {
                    Assertions.assertThat(book.getRating()).isGreaterThanOrEqualTo(minRating);
                    Assertions.assertThat(book.getGenre().getName()).isEqualTo(genreName);
                });
    }

    @Test
    void checkFindByTitleNamedQuery() {
        List<Book> books = repository.findByTitle("The Silent Ocean");
        Assertions.assertThat(books).isNotEmpty();
    }

    @Test
    void checkFindBooksByAuthorAndPeriod() {
        Integer authorId = 102;
        int fromYear = 2017;
        int toYear = 2018;

        List<Book> books = repository.findBooksByAuthorAndPeriod(authorId, fromYear, toYear);
        Assertions.assertThat(books)
                .isNotEmpty()
                .allSatisfy(book -> {
                    Assertions.assertThat(book.getAuthor().getId()).isEqualTo(authorId);
                    Assertions.assertThat(book.getReleaseYear()).isBetween(fromYear, toYear);
                });
    }

    @Test
    void checkFindByAuthorIdWithGraph() {
        Integer authorId = 101;
        List<Book> books = repository.findByAuthorIdWithGraph(authorId);

        Assertions.assertThat(books)
                .isNotEmpty()
                .allSatisfy(book -> {
                    Assertions.assertThat(book.getAuthor()).isNotNull();
                    Assertions.assertThat(book.getGenre()).isNotNull();
                });
    }
}