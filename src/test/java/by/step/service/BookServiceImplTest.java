package by.step.service;

import by.step.model.Book;
import by.step.service.impl.BookServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

class BookServiceImplTest {

    private static BookService bookService;

    @BeforeAll
    static void init() {
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
    void getAllBooks() throws InterruptedException {
        Date start = new Date();
        List<Book> allBooksFirst = bookService.getAllBooks();  // repo 5sec
        Assertions.assertThat(allBooksFirst).isNotNull();
        Date end1 = new Date();
        System.err.println("time to find all books (first attempt): "
                + (end1.getTime() - start.getTime()));

        bookService.getAllBooks();      // cache 5ms
        Date end2 = new Date();
        System.err.println("time to find all books (second attempt): "
                + (end2.getTime() - end1.getTime()));

        Thread.sleep(5000L);

        Date start3 = new Date();
        bookService.getAllBooks();      // cache 5ms
        Date end3 = new Date();
        System.err.println("third " + (end3.getTime()-start3.getTime()));

        Thread.sleep(2000L);

        Date start4 = new Date();
        bookService.getAllBooks();      // repo 5sec
        Date end4 = new Date();
        System.err.println("fourth " + (end4.getTime()-start4.getTime()));

        Date start5 = new Date();
        bookService.getAllBooks();
        Date end5 = new Date();
        System.err.println("fifth " + (end5.getTime()-start5.getTime()));



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
        Map<String, Serializable> miller = bookService.getAuthorStatistics("miller");
        Assertions.assertThat(miller).isNotEmpty();
    }
}