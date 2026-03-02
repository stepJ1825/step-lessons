package by.step.repository;

import by.step.ApplicationRunner;
import by.step.entity.Author;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = ApplicationRunner.class)
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void findAuthorsBySurname() {
        List<Author> brown = authorRepository.findAuthorsBySurrname("Brown"); //TODO: почему не падает ошибка
        Assertions.assertThat(brown).isNotEmpty();
    }
}