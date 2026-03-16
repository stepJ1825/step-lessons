package by.step.repository;

import by.step.entity.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest//(classes = ApplicationRunner.class)
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
//@Transactional
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
    void checkFindByReleaseYearBetweenWithPagination() {
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("releaseYear").ascending());

        Page<Book> page = repository.findByReleaseYearBetween(2019, 2020, pageRequest);

        Assertions.assertThat(page.getContent())
                .isNotEmpty()
                .allSatisfy(book ->
                        Assertions.assertThat(book.getReleaseYear()).isBetween(2019, 2020)
                );
    }

    @Test
    @Transactional
    void checkSaveAndFindById() {
        // используем уже существующую книгу из тестовых данных,
        // чтобы не конфликтовать с PK и автоинкрементом
        Book existing = repository.findAll(PageRequest.of(0, 1)).getContent().get(0);
        Integer id = existing.getId();
        String originalTitle = existing.getTitle();
        String updatedTitle = originalTitle + " (updated)";

        existing.setTitle(updatedTitle);
        Book saved = repository.save(existing);

        Assertions.assertThat(saved.getId()).isEqualTo(id);
        Assertions.assertThat(saved.getTitle()).isEqualTo(updatedTitle);

        Book found = repository.findById(id).orElseThrow();
        Assertions.assertThat(found.getTitle()).isEqualTo(updatedTitle);
    }

    @Test
    @Transactional
    void checkSaveAllAndDeleteAllById() {
        // берём несколько существующих книг, "сохраняем" (update/no-op) и удаляем
        List<Book> existing = repository.findAll(PageRequest.of(0, 2)).getContent();

        List<Book> saved = repository.saveAll(existing);

        List<Integer> ids = saved.stream()
                                 .map(Book::getId)
                                 .collect(Collectors.toList());

        repository.deleteAllById(ids);

        ids.forEach(id ->
                Assertions.assertThat(repository.findById(id)).isEmpty()
        );
    }

    @Test
    @Transactional
    void checkDeleteById() {
        Integer id = repository.findAll(PageRequest.of(0, 1))
                               .getContent()
                               .get(0)
                               .getId();

        repository.deleteById(id);

        Assertions.assertThat(repository.findById(id)).isEmpty();
    }
}

