package by.step.repository.impl;

import by.step.ApplicationRunner;
import by.step.model.simple.Book;
import by.step.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest(classes = ApplicationRunner.class)
class BookSpringJdbcRepositoryImplTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void getAllBooks() {
        List<Book> allBooks = bookRepository.getAllBooks();
    }

    @Test
    void updateAllBooks() {
    }
}