package by.step.service.impl;


import by.step.entity.Author;
import by.step.entity.Book;
import by.step.entity.Genre;
import by.step.repository.BookRepository;
import by.step.service.AuthorService;
import org.assertj.core.data.Offset;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class BookServiceImplPrivateMethodTest {

    @Mock
    private BookRepository repository;

    @Mock
    private AuthorService authorService;

    private BookServiceImpl bookService;

    private Book book1;
    private Book book2;
    private Book book3;
    private List<Book> bookList;
    private Author author1;
    private Author author2;
    private Genre genre1;
    private Genre genre2;

    @BeforeEach
    void setUp() {
        // Создаем реальный экземпляр, затем оборачиваем в spy
        BookServiceImpl realService = new BookServiceImpl(repository, authorService);
        bookService = spy(realService); // Ручное создание spy

        genre1 = Genre.builder()
                      .id(1)
                      .name("Роман")
                      .build();

        genre2 = Genre.builder()
                      .id(2)
                      .name("Детектив")
                      .build();

        author1 = Author.builder()
                        .id(1)
                        .firstName("Лев")
                        .surname("Толстой")
                        .build();

        author2 = Author.builder()
                        .id(2)
                        .firstName("Фёдор")
                        .surname("Достоевский")
                        .build();

        book1 = Book.builder()
                    .id(1)
                    .title("Война и мир")
                    .author(author1)
                    .genre(genre1)
                    .rating(4.8f)
                    .releaseYear(1869)
                    .build();

        book2 = Book.builder()
                    .id(2)
                    .title("Преступление и наказание")
                    .author(author2)
                    .genre(genre1)
                    .rating(4.9f)
                    .releaseYear(1866)
                    .build();

        book3 = Book.builder()
                    .id(3)
                    .title("Анна Каренина")
                    .author(author1)
                    .genre(genre1)
                    .rating(4.7f)
                    .releaseYear(1877)
                    .build();

        bookList = Arrays.asList(book1, book2, book3);
    }

    @Test
    void testPrivateGetAverageRating_WithValidBooks() throws Exception {
        // Given
        List<Book> books = Arrays.asList(book1, book2, book3);
        double expectedAverage = (4.8 + 4.9 + 4.7) / 3;

        // When
        Method privateMethod = BookServiceImpl.class.getDeclaredMethod("getAverageRating", List.class);
        privateMethod.setAccessible(true);
        double result = (double) privateMethod.invoke(bookService, books);

        // Then
        assertThat(result).isCloseTo(expectedAverage, Offset.offset(0.01));
        verify(bookService, never()).getAllBooks();
    }

    @Test
    void testPrivateGetAverageRating_WithEmptyList() throws Exception {
        // Given
        List<Book> emptyBooks = Collections.emptyList();

        // When
        Method privateMethod = BookServiceImpl.class.getDeclaredMethod("getAverageRating", List.class);
        privateMethod.setAccessible(true);
        double result = (double) privateMethod.invoke(bookService, emptyBooks);

        // Then
        assertThat(result).isZero();
    }

    @Test
    void testPrivateGetAverageRating_WithSingleBook() throws Exception {
        // Given
        List<Book> singleBook = Collections.singletonList(book1);
        double expectedAverage = 4.8;

        // When
        Method privateMethod = BookServiceImpl.class.getDeclaredMethod("getAverageRating", List.class);
        privateMethod.setAccessible(true);
        double result = (double) privateMethod.invoke(bookService, singleBook);

        // Then
        assertThat(result).isCloseTo(expectedAverage, Percentage.withPercentage(0.01));
        assertThat(result).isCloseTo(expectedAverage, Offset.offset(0.00001));
    }

    @Test
    void testPrivateGetAverageRating_WithBooksWithSameRating() throws Exception {
        // Given
        Book book4 = Book.builder()
                         .id(4)
                         .title("Тестовая книга")
                         .author(author1)
                         .genre(genre1)
                         .rating(5.0f)
                         .build();

        Book book5 = Book.builder()
                         .id(5)
                         .title("Еще одна книга")
                         .author(author1)
                         .genre(genre1)
                         .rating(5.0f)
                         .build();

        List<Book> books = Arrays.asList(book4, book5);
        double expectedAverage = 5.0;

        // When
        Method privateMethod = BookServiceImpl.class.getDeclaredMethod("getAverageRating", List.class);
        privateMethod.setAccessible(true);
        double result = (double) privateMethod.invoke(bookService, books);

        // Then
        assertThat(result).isEqualTo(expectedAverage);
    }
}
