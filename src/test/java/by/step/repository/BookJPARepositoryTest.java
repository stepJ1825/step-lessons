package by.step.repository;

import by.step.ApplicationRunner;
import by.step.model.jpa.AuthorJPA;
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

    @Test
    void checkFindByAuthor(){
        AuthorJPA author = AuthorJPA.builder().id(102)
                .firstName("Michael")
                .surname("Brown")
                .build();
        List<BookJPA> booksByAuthor = repository.findByAuthor(author);
        Assertions.assertThat(booksByAuthor).isNotEmpty();
    }

    @Test
    void checkFindByAuthorNotNull(){
        List<BookJPA> byAuthorNotNull = repository.findByAuthorNotNull();
        Assertions.assertThat(byAuthorNotNull).isNotEmpty();
    }

    @Test
    void checkFindByAuthorIsNull(){
        List<BookJPA> byAuthorNotNull = repository.findByAuthorIsNull();
        Assertions.assertThat(byAuthorNotNull).isEmpty();
    }

    @Test
    void checkFindByAuthorNameContains(){
        List<BookJPA> booksByAuthor = repository.findByAuthorFirstNameContains("Michael");
        Assertions.assertThat(booksByAuthor).isNotEmpty();
    }

    @Test
    void checkFindAllNative() {
        List<BookJPA> all = repository.findAllNative();
        Assertions.assertThat(all).isNotNull();
    }



}