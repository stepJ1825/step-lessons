package by.step.service.impl;

import by.step.entity.Author;
import by.step.entity.Book;
import by.step.entity.Genre;
import by.step.repository.BookRepository;
import by.step.service.AuthorService;
import by.step.service.BookFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.Serializable;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplMockSpyCaptorTest {

    @Mock
    private BookRepository repository;

    @Mock
    private AuthorService authorService;

    private BookServiceImpl bookService;

    @Captor
    private ArgumentCaptor<Book> bookCaptor;

    @Captor
    private ArgumentCaptor<Integer> integerCaptor;

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

    // ==================== TESTS FOR addBook ====================

    @Test
    void addBook_ShouldSaveBook_WhenAuthorIsValid() {
        // Given
        doNothing().when(authorService).validateAuthor(any(Author.class));
        when(repository.save(any(Book.class))).thenReturn(book1);

        // When
        bookService.addBook(book1);

        // Then
        verify(authorService, times(1)).validateAuthor(book1.getAuthor());
        verify(repository, times(1)).save(bookCaptor.capture());

        Book capturedBook = bookCaptor.getValue();
        assertThat(capturedBook).isEqualTo(book1);
    }

    @Test
    void addBook_ShouldThrowException_WhenAuthorIsInvalid() {
        // Given
        doThrow(new IllegalArgumentException("Author not found"))
                .when(authorService).validateAuthor(any(Author.class));

        // When & Then
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bookService.addBook(book1);
        });

        verify(authorService, times(1)).validateAuthor(any(Author.class));
        verify(repository, never()).save(any(Book.class));
    }

    // ==================== TESTS FOR removeBook ====================

    @Test
    void removeBook_ShouldDeleteBook_WhenIdExists() {
        // Given
        int bookId = 1;
        doNothing().when(repository).deleteById(anyInt());

        // When
        bookService.removeBook(bookId);

        // Then
        verify(repository, times(1)).deleteById(integerCaptor.capture());
        assertThat(integerCaptor.getValue()).isEqualTo(bookId);
    }

    @Test
    void removeBook_ShouldNotThrowException_WhenIdDoesNotExist() {
        // Given
        int bookId = 999;
        doNothing().when(repository).deleteById(anyInt());

        // When
        bookService.removeBook(bookId);

        // Then
        verify(repository, atLeastOnce()).deleteById(bookId);
    }

    // ==================== TESTS FOR findBooksByAuthor ====================

    @Test
    void findBooksByAuthor_ShouldReturnBooks_WhenAuthorExists() {
        // Given
        String surname = "Толстой";
        List<Book> expectedBooks = Arrays.asList(book1, book3);
        when(repository.findByAuthorSurname(surname)).thenReturn(expectedBooks);

        // When
        List<Book> result = bookService.findBooksByAuthor(surname);

        // Then
        verify(repository, times(1)).findByAuthorSurname(surname);
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(book1, book3);
    }

    @Test
    void findBooksByAuthor_ShouldReturnEmptyList_WhenAuthorNotFound() {
        // Given
        String surname = "Неизвестный";
        when(repository.findByAuthorSurname(surname)).thenReturn(Collections.emptyList());

        // When
        List<Book> result = bookService.findBooksByAuthor(surname);

        // Then
        verify(repository, atMostOnce()).findByAuthorSurname(surname);
        assertThat(result).isEmpty();
    }

    // ==================== TESTS FOR findBooksByYearRange ====================

    @Test
    void findBooksByYearRange_ShouldReturnBooksInRange() {
        // Given
        int start = 1860;
        int end = 1870;
        List<Book> expectedBooks = Arrays.asList(book1, book2);
        when(repository.findByReleaseYearBetween(start, end)).thenReturn(expectedBooks);

        // When
        List<Book> result = bookService.findBooksByYearRange(start, end);

        // Then
        verify(repository, times(1)).findByReleaseYearBetween(start, end);
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(book1, book2);
    }

    // ==================== TESTS FOR getAllBooks ====================

    @Test
    void getAllBooks_ShouldReturnAllBooks() {
        // Given
        when(repository.findAll()).thenReturn(bookList);

        // When
        List<Book> result = bookService.getAllBooks();

        // Then
        verify(repository, times(1)).findAll();
        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(book1, book2, book3);
    }

    @Test
    void getAllBooks_ShouldReturnEmptyList_WhenNoBooks() {
        // Given
        when(repository.findAll()).thenReturn(Collections.emptyList());

        // When
        List<Book> result = bookService.getAllBooks();

        // Then
        verify(repository, atLeastOnce()).findAll();
        assertThat(result).isEmpty();
    }

    // ==================== TESTS FOR getBooksByFilter ====================

    @Test
    void getBooksByFilter_ShouldReturnFilteredBooks() {
        // Given
        when(repository.findAll()).thenReturn(bookList);
        BookFilter filter = mock(BookFilter.class);
        when(filter.filter(book1)).thenReturn(true);
        when(filter.filter(book2)).thenReturn(false);
        when(filter.filter(book3)).thenReturn(true);

        // When
        List<Book> result = bookService.getBooksByFilter(filter);

        // Then
        verify(repository, times(1)).findAll();
        verify(filter, times(3)).filter(any(Book.class));
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(book1, book3);
    }

    // ==================== TESTS FOR getAverageRating ====================

    @Test
    void getAverageRating_ShouldReturnCorrectAverage() {
        // Given
        when(repository.findAll()).thenReturn(bookList);

        // When
        float result = bookService.getAverageRating();

        // Then
        verify(repository, times(1)).findAll();
        float expectedAverage = (4.8f + 4.9f + 4.7f) / 3;
        assertThat(result).isEqualTo(expectedAverage);
    }

    @Test
    void getAverageRating_ShouldReturnZero_WhenNoBooks() {
        // Given
        when(repository.findAll()).thenReturn(Collections.emptyList());

        // When
        float result = bookService.getAverageRating();

        // Then
        verify(repository, times(1)).findAll();
        assertThat(result).isZero();
    }

    // ==================== TESTS FOR getBooksGroupedByGenre ====================

    @Test
    void getBooksGroupedByGenre_ShouldReturnGroupedBooks() {
        // Given
        when(repository.findAll()).thenReturn(bookList);
        Genre genre3 = Genre.builder().id(3).name("Фантастика").build();
        Book book4 = Book.builder()
                         .id(4)
                         .title("1984")
                         .genre(genre3)
                         .rating(4.5f)
                         .build();
        List<Book> booksWithDifferentGenres = Arrays.asList(book1, book2, book4);

        when(repository.findAll()).thenReturn(booksWithDifferentGenres);

        // When
        Map<String, List<Book>> result = bookService.getBooksGroupedByGenre();

        // Then
        verify(repository, times(1)).findAll();
        assertThat(result).hasSize(2);
        assertThat(result.get("Роман")).hasSize(2);
        assertThat(result.get("Фантастика")).hasSize(1);
    }

    // ==================== TESTS FOR findById ====================

    @Test
    void findById_ShouldReturnBook_WhenIdExists() {
        // Given
        int bookId = 1;
        when(repository.findById(bookId)).thenReturn(Optional.of(book1));

        // When
        Book result = bookService.findById(bookId);

        // Then
        verify(repository, times(1)).findById(bookId);
        assertThat(result).isEqualTo(book1);
    }

    @Test
    void findById_ShouldReturnEmptyBook_WhenIdDoesNotExist() {
        // Given
        int bookId = 999;
        when(repository.findById(bookId)).thenReturn(Optional.empty());

        // When
        Book result = bookService.findById(bookId);

        // Then
        verify(repository, times(1)).findById(bookId);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(0); // плохая практика возврата 0 у сервиса/репозитория
    }

    // ==================== TESTS FOR searchBooks ====================

    @Test
    void searchBooks_ShouldFindByTitle_IgnoreCase() {
        // Given
        String keyword = "война";
        when(repository.findAll()).thenReturn(bookList);

        // When
        List<Book> result = bookService.searchBooks(keyword);

        // Then
        verify(repository, times(1)).findAll();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).contains("Война");
    }

    @Test
    void searchBooks_ShouldFindByAuthorFirstName_IgnoreCase() {
        // Given
        String keyword = "лев";
        when(repository.findAll()).thenReturn(bookList);

        // When
        List<Book> result = bookService.searchBooks(keyword);

        // Then
        verify(repository, atLeastOnce()).findAll();
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(book1, book3);
    }

    @Test
    void searchBooks_ShouldFindByAuthorSurname_IgnoreCase() {
        // Given
        String keyword = "достоевский";
        when(repository.findAll()).thenReturn(bookList);

        // When
        List<Book> result = bookService.searchBooks(keyword);

        // Then
        verify(repository, times(1)).findAll();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getAuthor().getSurname()).isEqualTo("Достоевский");
    }

    @Test
    void searchBooks_ShouldReturnEmptyList_WhenNoMatch() {
        // Given
        String keyword = "несуществующее";
        when(repository.findAll()).thenReturn(bookList);

        // When
        List<Book> result = bookService.searchBooks(keyword);

        // Then
        verify(repository, times(1)).findAll();
        assertThat(result).isEmpty();
    }

    // ==================== TESTS FOR getBookTitlesAsString ====================

    @Test
    void getBookTitlesAsString_ShouldReturnCommaSeparatedTitles() {
        // Given
        when(repository.findAll()).thenReturn(bookList);

        // When
        String result = bookService.getBookTitlesAsString();

        // Then
        verify(repository, times(1)).findAll();
        String expected = "Война и мир, Преступление и наказание, Анна Каренина";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getBookTitlesAsString_ShouldReturnEmptyString_WhenNoBooks() {
        // Given
        when(repository.findAll()).thenReturn(Collections.emptyList());

        // When
        String result = bookService.getBookTitlesAsString();

        // Then
        verify(repository, times(1)).findAll();
        assertThat(result).isEmpty();
    }

    // ==================== TESTS FOR getAuthorStatistics ====================

    @Test
    void getAuthorStatistics_ShouldReturnCorrectStatistics() {
        // Given
        String surname = "Толстой";
        List<Book> authorBooks = Arrays.asList(book1, book3);
        when(repository.findByAuthorSurname(surname)).thenReturn(authorBooks);

        // When
        Map<String, Serializable> result = bookService.getAuthorStatistics(surname);

        // Then
        verify(repository, times(1)).findByAuthorSurname(surname);

        assertThat(result).containsKeys("averageRating", "bookCount", "favouriteGenre");
        assertThat(result.get("bookCount")).isEqualTo(2);
        assertThat((Double) result.get("averageRating")).isEqualTo((4.8 + 4.7) / 2);
        assertThat(result.get("favouriteGenre")).isEqualTo("Роман");
    }

    @Test
    void getAuthorStatistics_ShouldThrowException_WhenNoBooksFound() {
        // Given
        String surname = "Неизвестный";
        when(repository.findByAuthorSurname(surname)).thenReturn(Collections.emptyList());

        // When & Then
        assertThatThrownBy(() -> bookService.getAuthorStatistics(surname))
                .isInstanceOf(NoSuchElementException.class);

        verify(repository, times(1)).findByAuthorSurname(surname);
    }

    // ==================== TEST WITH SPY DEMONSTRATION ====================

    @Test
    void getBookTitlesAsString_ShouldCallGetAllBooks_UsingSpy() {
        // Given
        List<Book> books = Arrays.asList(book1, book2);
        doReturn(books).when(bookService).getAllBooks();

        // When
        String result = bookService.getBookTitlesAsString();

        // Then
        verify(bookService, times(1)).getAllBooks();
        assertThat(result).isEqualTo("Война и мир, Преступление и наказание");
    }

    @Test
    void getAverageRating_ShouldUseSpy_ToMockPartialBehavior() {
        // Given
        List<Book> customBooks = Arrays.asList(book1, book2);
        when(repository.findAll()).thenReturn(customBooks);

        // When
        float result = bookService.getAverageRating();

        // Then
        verify(repository, times(1)).findAll();
        float expectedAverage = (4.8f + 4.9f) / 2;
        assertThat(result).isEqualTo(expectedAverage);
    }

    // ==================== TEST WITH CAPTOR AND VERIFY COMPLEX ====================

    @Test
    void addBook_ShouldCaptureAndVerifyBookDetails() {
        // Given
        ArgumentCaptor<Author> authorCaptor = ArgumentCaptor.forClass(Author.class);
        doNothing().when(authorService).validateAuthor(authorCaptor.capture());
        when(repository.save(any(Book.class))).thenReturn(book1);

        // When
        bookService.addBook(book1);

        // Then
        verify(repository).save(bookCaptor.capture());
        Book captured = bookCaptor.getValue();

        assertThat(captured.getTitle()).isEqualTo("Война и мир");
        assertThat(captured.getAuthor().getSurname()).isEqualTo("Толстой");
        assertThat(captured.getRating()).isEqualTo(4.8f);

        // Проверяем захваченный Author
        Author capturedAuthor = authorCaptor.getValue();
        assertThat(capturedAuthor.getSurname()).isEqualTo("Толстой");

        // Проверяем, что не вызывался с Достоевским
        verify(authorService, never()).validateAuthor(argThat(author ->
                author.getSurname().equals("Достоевский")
        ));
    }

    @Test
    void removeBook_ShouldCaptureAndVerifyMultipleIds() {
        // Given
        doNothing().when(repository).deleteById(anyInt());

        // When
        bookService.removeBook(1);
        bookService.removeBook(2);
        bookService.removeBook(3);

        // Then
        verify(repository, times(3)).deleteById(integerCaptor.capture());
        List<Integer> capturedIds = integerCaptor.getAllValues();

        assertThat(capturedIds).containsExactly(1, 2, 3);
        verify(repository, atLeastOnce()).deleteById(anyInt());
    }

    // ==================== TEST WITH ANY MATCHER ====================

    @Test
    void findBooksByAuthor_ShouldUseAnyMatcher() {
        // Given
        List<Book> expectedBooks = Arrays.asList(book1, book3);
        when(repository.findByAuthorSurname(anyString())).thenReturn(expectedBooks);

        // When
        List<Book> result = bookService.findBooksByAuthor("Толстой");

        // Then
        verify(repository, times(1)).findByAuthorSurname(anyString());
        assertThat(result).hasSize(2);
    }
}