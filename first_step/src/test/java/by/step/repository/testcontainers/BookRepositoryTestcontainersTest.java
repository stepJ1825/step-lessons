package by.step.repository.testcontainers;

import by.step.FirstStepApplication;
import by.step.dto.BookDetailsDTO;
import by.step.entity.Author;
import by.step.entity.Book;
import by.step.entity.Genre;
import by.step.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Comparator;
import java.util.List;

@SpringBootTest(classes = FirstStepApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
class BookRepositoryTestcontainersTest extends PostgresTestcontainersBase {

    @Autowired
    private BookRepository repository;

    @Test
    void checkFindAll() {
        List<Book> all = repository.findAll();
        Assertions.assertThat(all).isNotNull().isNotEmpty();
    }

    @Test
    void checkFindByAuthor() {
        Author author = Author.builder()
                .id(102)
                .firstName("Michael")
                .surname("Brown")
                .build();

        List<Book> booksByAuthor = repository.findByAuthor(author);
        Assertions.assertThat(booksByAuthor).isNotEmpty();
        Assertions.assertThat(booksByAuthor).allSatisfy(book ->
                Assertions.assertThat(book.getAuthor().getSurname()).isEqualTo("Brown")
        );
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
    void checkFindByReleaseYearBetween() {
        List<Book> books = repository.findByReleaseYearBetween(2019, 2020);

        Assertions.assertThat(books)
                .isNotEmpty()
                .allMatch(book -> book.getReleaseYear() >= 2019 && book.getReleaseYear() <= 2020);
    }

    @Test
    void checkFindByAuthorNotNull() {
        List<Book> byAuthorNotNull = repository.findByAuthorNotNull();
        Assertions.assertThat(byAuthorNotNull).isNotEmpty();
        Assertions.assertThat(byAuthorNotNull).allSatisfy(book ->
                Assertions.assertThat(book.getAuthor()).isNotNull()
        );
    }

    @Test
    void checkFindByAuthorIsNull() {
        List<Book> byAuthorIsNull = repository.findByAuthorIsNull();
        Assertions.assertThat(byAuthorIsNull).isEmpty();
    }

    @Test
    void checkFindByAuthorFirstNameContains() {
        Book testBook = repository.findAll().stream().findAny().orElseThrow();
        String testFirstname = testBook.getAuthor().getFirstName();
        List<Book> booksByAuthor = repository.findByAuthorFirstNameContains(testFirstname);

        Assertions.assertThat(booksByAuthor)
                .isNotEmpty()
                .allSatisfy(book ->
                        Assertions.assertThat(book.getAuthor().getFirstName())
                                .contains(testFirstname)
                );
    }

    @Test
    void checkFindAllNative() {
        List<Book> all = repository.findAllNative();
        Assertions.assertThat(all).isNotEmpty();
    }

    @Test
    void checkFindTopRatedAfterYear() {
        int year = 2022;
        List<Book> books = repository.findTopRatedAfterYear(year);

        Assertions.assertThat(books).isNotEmpty();
        Assertions.assertThat(books).allMatch(book -> book.getReleaseYear() > year);
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
            // b.* (6 columns) + first_name + surname + genre_name
            Assertions.assertThat(row.length).isGreaterThanOrEqualTo(6);

            // rating is the 6th column in books: id(0),title(1),author_id(2),genre_id(3),release_year(4),rating(5)
            Float rating = ((Number) row[5]).floatValue();
            Assertions.assertThat(rating).isGreaterThanOrEqualTo(minRating);
        }
    }

    @Test
    void checkFindBookDetailsByYear() {
        int year = 2020;
        List<BookDetailsDTO> details = repository.findBookDetailsByYear(year);

        Assertions.assertThat(details).isNotEmpty();
        details.forEach(dto -> {
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

        Assertions.assertThat(books).isNotEmpty()
                .allMatch(book -> book.getTitle().contains(keyword));

        Assertions.assertThat(booksByLowerCase).isNotEmpty()
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

        Assertions.assertThat(books).isNotEmpty();
        books.forEach(book -> {
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

        Assertions.assertThat(books).isNotEmpty();
        books.forEach(book -> {
            Assertions.assertThat(book.getAuthor().getId()).isEqualTo(authorId);
            Assertions.assertThat(book.getReleaseYear()).isBetween(fromYear, toYear);
        });
    }

    @Test
    void checkFindByAuthorIdWithGraph() {
        Integer authorId = 101;
        List<Book> books = repository.findByAuthorIdWithGraph(authorId);

        Assertions.assertThat(books).isNotEmpty();
        books.forEach(book -> {
            Assertions.assertThat(book.getAuthor()).isNotNull();
            Assertions.assertThat(book.getGenre()).isNotNull();
        });
    }
}

