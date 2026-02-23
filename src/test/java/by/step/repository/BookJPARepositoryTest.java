package by.step.repository;

import by.step.ApplicationRunner;
import by.step.model.jpa.BookJPA;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = ApplicationRunner.class)
class BookJPARepositoryTest {

    @Autowired
    private BookJPARepository repository;

    @Test
    void checkFindAll() {
        List<BookJPA> all = repository.findAll();
        Assertions.assertThat(all).isNotNull();
    }
}