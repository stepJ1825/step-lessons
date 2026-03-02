package by.step.repository;

import by.step.ApplicationRunner;
import by.step.entity.Author;
import by.step.entity.Book;
import by.step.entity.Genre;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(classes = ApplicationRunner.class)
class BookSecondRepositoryTest {

    @Autowired
    private BookSecondRepository repository;

    @Test
    void checkFindAllPageable() {
        PageRequest pageRequest = PageRequest.of(0, 10);

        Page<Book> page = repository.findAll(pageRequest);

        Assertions.assertThat(page.getContent())
                .isNotNull()
                .isNotEmpty()
                .hasSizeLessThanOrEqualTo(10);
    }

    @Test
    void checkFindAllSortedByTitle() {
        List<Book> books = repository.findAll(Sort.by(Sort.Direction.ASC, "title"));

        Assertions.assertThat(books)
                .isNotEmpty()
                .allSatisfy(book -> Assertions.assertThat(book.getTitle()).isNotBlank());
    }

    @Test
    void checkFindByTitleContainingIgnoreCase() {
        PageRequest pageRequest = PageRequest.of(0, 10);

        Page<Book> page = repository.findByTitleContainingIgnoreCase("ocean", pageRequest);

        Assertions.assertThat(page.getContent())
                .isNotEmpty()
                .allSatisfy(book ->
                        Assertions.assertThat(book.getTitle().toLowerCase()).contains("ocean")
                );
    }

    @Test
    void checkFindByYearBetweenWithPagination() {
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("year").ascending());

        Page<Book> page = repository.findByYearBetween(2019, 2020, pageRequest);

        Assertions.assertThat(page.getContent())
                .isNotEmpty()
                .allSatisfy(book ->
                        Assertions.assertThat(book.getYear()).isBetween(2019, 2020)
                );
    }

    @Test
    void checkSaveAndFindById() {
        Author author = Author.builder()
                              .id(101)
                              .build();
        Genre genre = Genre.builder()
                           .id(1)
                           .build();

        Book book = Book.builder()
                        .title("Test CRUD Book")
                        .author(author)
                        .genre(genre)
                        .year(2024)
                        .rating(4.5f)
                        .build();

        Book saved = repository.save(book);

        Assertions.assertThat(saved.getId()).isGreaterThan(0);

        Book found = repository.findById(saved.getId()).orElseThrow();
        Assertions.assertThat(found.getTitle()).isEqualTo("Test CRUD Book");
    }

    @Test
    void checkSaveAllAndDeleteAllById() {
        Author author = Author.builder()
                              .id(101)
                              .build();
        Genre genre = Genre.builder()
                           .id(1)
                           .build();

        Book first = Book.builder()
                         .title("Bulk Book 1")
                         .author(author)
                         .genre(genre)
                         .year(2024)
                         .rating(4.0f)
                         .build();

        Book second = Book.builder()
                          .title("Bulk Book 2")
                          .author(author)
                          .genre(genre)
                          .year(2024)
                          .rating(4.1f)
                          .build();

        List<Book> saved = repository.saveAll(List.of(first, second));

        List<Integer> ids = saved.stream()
                                 .map(Book::getId)
                                 .collect(Collectors.toList());

        repository.deleteAllById(ids);

        ids.forEach(id ->
                Assertions.assertThat(repository.findById(id)).isEmpty()
        );
    }

    @Test
    void checkDeleteById() {
        Author author = Author.builder()
                              .id(101)
                              .build();
        Genre genre = Genre.builder()
                           .id(1)
                           .build();

        Book book = Book.builder()
                        .title("To Be Deleted")
                        .author(author)
                        .genre(genre)
                        .year(2024)
                        .rating(4.2f)
                        .build();

        Book saved = repository.save(book);
        Integer id = saved.getId();

        repository.deleteById(id);

        Assertions.assertThat(repository.findById(id)).isEmpty();
    }
}

