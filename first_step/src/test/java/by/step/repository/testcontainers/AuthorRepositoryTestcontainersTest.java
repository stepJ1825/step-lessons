package by.step.repository.testcontainers;

import by.step.FirstStepApplication;
import by.step.entity.Author;
import by.step.repository.AuthorRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest(classes = FirstStepApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
class AuthorRepositoryTestcontainersTest extends PostgresTestcontainersBase {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void findAuthorsBySurname() {
        List<Author> brown = authorRepository.findAuthorsBySurname("Brown");
        Assertions.assertThat(brown).isNotEmpty();
        Assertions.assertThat(brown).allSatisfy(a ->
                Assertions.assertThat(a.getSurname()).isEqualTo("Brown")
        );
    }

    @Test
    void findBySurnameEndingWithNative() {
        List<Author> list = authorRepository.findBySurnameEndingWithNative("oWn");
        Assertions.assertThat(list).isNotEmpty();
        Assertions.assertThat(list).allSatisfy(a ->
                Assertions.assertThat(a.getSurname().toLowerCase()).endsWith("own")
        );
    }
}

