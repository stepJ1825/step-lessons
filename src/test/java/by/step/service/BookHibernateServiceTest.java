package by.step.service;

import by.step.ApplicationRunner;
import by.step.entity.Author;
import by.step.entity.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest(classes = ApplicationRunner.class)
class BookHibernateServiceTest {

    @Autowired
    private BookHibernateService service;

    @Test
    void searchBooks_withAllNullParams_returnsLimitedList() {
        List<Book> books = service.searchBooks(null, null, null, null, null, null);

        Assertions.assertThat(books)
                .isNotNull()
                .isNotEmpty()
                .hasSizeLessThanOrEqualTo(50);
    }

    @Test
    void searchBooks_filterByTitleAndYearAndRating() {
        List<Book> books = service.searchBooks(
                "Winter", // есть несколько книг с Winter в названии
                null,
                null,
                2018,
                2021,
                4.3f
        );

        Assertions.assertThat(books)
                .isNotEmpty()
                .allSatisfy(book -> {
                    Assertions.assertThat(book.getTitle().toLowerCase()).contains("winter");
                    Assertions.assertThat(book.getYear()).isBetween(2018, 2021);
                    Assertions.assertThat(book.getRating()).isGreaterThanOrEqualTo(4.3f);
                });
    }

    @Test
    void searchBooks_filterByAuthorAndGenre() {
        // данные из миграций: автор 102 (Michael Brown), жанр 2 (FANTASY)
        List<Book> books = service.searchBooks(
                null,
                102,
                2,
                null,
                null,
                null
        );

        Assertions.assertThat(books)
                .isNotEmpty()
                .allSatisfy(book -> {
                    Assertions.assertThat(book.getAuthor().getId()).isEqualTo(102);
                    Assertions.assertThat(book.getGenre().getId()).isEqualTo(2);
                });
    }

    @Test
    void getAverageRatingByAuthor_returnsAuthorsWithAvgRatingAtLeastFour() {
        Map<Author, Double> avgByAuthor = service.getAverageRatingByAuthor();

        Assertions.assertThat(avgByAuthor)
                .isNotNull()
                .isNotEmpty();

        avgByAuthor.forEach((author, avg) -> {
            Assertions.assertThat(author).isNotNull();
            Assertions.assertThat(avg).isGreaterThanOrEqualTo(4.0);
        });
    }

    @Test
    void findWithGraph_loadsAuthorAndGenre() {
        List<Book> books = service.findWithGraph(1);

        Assertions.assertThat(books)
                .isNotEmpty()
                .allSatisfy(book -> {
                    Assertions.assertThat(book.getAuthor()).isNotNull();
                    Assertions.assertThat(book.getGenre()).isNotNull();
                });
    }

    @Test
    void searchWithHibernateCriteria_searchByKeywordInTitleOrAuthorSurname() {
        List<Book> books = service.searchWithHibernateCriteria("Ocean");

        Assertions.assertThat(books)
                .isNotEmpty()
                .allSatisfy(book -> {
                    String title = book.getTitle().toLowerCase();
                    String surname = book.getAuthor().getSurname().toLowerCase();
                    Assertions.assertThat(title.contains("ocean") || surname.contains("ocean")).isTrue();
                });
    }
}

