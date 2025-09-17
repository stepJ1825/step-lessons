package by.step.service;

import by.step.model.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class BookServiceImplTest {

    private static BookService bookService;

    @BeforeAll
    static void init(){
        bookService = new BookServiceImpl();
    }

    @Test
    @Disabled
    void addBook() {
//        assertThrows(IllegalStateException.class, () -> bookService.addBook(new Book()));
        bookService.addBook(new Book());
    }

    @Test
    void removeBook() {
    }

    @Test
    void findBooksByAuthor() {
        String author = "Miller";
        List<Book> booksByAuthor = bookService.findBooksByAuthor(author);
        Assertions.assertThat(booksByAuthor).isNotEmpty();
    }

    @Test
    void findBooksByYearRange() {
    }

    @Test
    void getAllBooks() {
    }

    @Test
    void getBooksByFilter() {
    }

    @Test
    void getAverageRating() {
    }

    @Test
    void getBooksGroupedByGenre() {
    }

    @Test
    void findById() {
    }

    @Test
    void searchBooks() {
    }

    @Test
    void getBookTitlesAsString() {
    }

    @Test
    void getAuthorStatistics() {
    }
}