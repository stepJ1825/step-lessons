package by.step.repository;

import by.step.ApplicationRunner;
import by.step.entity.Author;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

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
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void findAuthorsBySurname() {
        List<Author> brown = authorRepository.findAuthorsBySurname("Brown");
        Assertions.assertThat(brown).isNotEmpty();
    }

    @Test
    void findBySurnameEndingWithNative(){
        List<Author> list = authorRepository.findBySurnameEndingWithNative("oWn");
        Assertions.assertThat(list).isNotEmpty();
    }
}