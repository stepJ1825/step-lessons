package by.step.integration;

import by.step.entity.Author;
import by.step.entity.Book;
import by.step.entity.Genre;
import by.step.repository.AuthorRepository;
import by.step.repository.BookRepository;
import by.step.repository.GenreRepository;
import by.step.service.BookFilter;
import by.step.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class BookIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Author author1;
    private Author author2;
    private Genre genre1;
    private Genre genre2;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    void setUp() {
        // Очистка баз данных
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        genreRepository.deleteAll();

        // Создание и сохранение жанров
        genre1 = new Genre();
        genre1.setName("Роман");
        genre1 = genreRepository.save(genre1);

        genre2 = new Genre();
        genre2.setName("Детектив");
        genre2 = genreRepository.save(genre2);

        // Создание и сохранение авторов
        author1 = new Author();
        author1.setFirstName("Лев");
        author1.setSurname("Толстой");
        author1 = authorRepository.save(author1);

        author2 = new Author();
        author2.setFirstName("Фёдор");
        author2.setSurname("Достоевский");
        author2 = authorRepository.save(author2);

        // Создание книг с уже сохраненными авторами и жанрами
        book1 = new Book();
        book1.setTitle("Война и мир");
        book1.setAuthor(author1);
        book1.setGenre(genre1);
        book1.setRating(4.8f);
        book1.setReleaseYear(1869);

        book2 = new Book();
        book2.setTitle("Преступление и наказание");
        book2.setAuthor(author2);
        book2.setGenre(genre1);
        book2.setRating(4.9f);
        book2.setReleaseYear(1866);

        book3 = new Book();
        book3.setTitle("Анна Каренина");
        book3.setAuthor(author1);
        book3.setGenre(genre1);
        book3.setRating(4.7f);
        book3.setReleaseYear(1877);
    }

    // ==================== КОНТРОЛЛЕР ТЕСТЫ ====================

    @Test
    void addBook_ShouldAddBookAndReturnSuccessMessage() throws Exception {
        // Given
        Book newBook = Book.builder()
                .title("Идиот")
                .author(author2)
                .genre(genre1)
                .rating(4.6f)
                .releaseYear(1869)
                .build();

        // When & Then
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newBook)))
                .andExpect(status().isOk())
                .andExpect(content().string("Книга успешно добавлена!"));

        // Verify
        List<Book> books = bookService.getAllBooks();
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Идиот");
    }

    @Test
    void getAllBooks_ShouldReturnAllBooks() throws Exception {
        // Given
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        // When & Then
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].title").value("Война и мир"))
                .andExpect(jsonPath("$[1].title").value("Преступление и наказание"))
                .andExpect(jsonPath("$[2].title").value("Анна Каренина"));
    }

    @Test
    void findBooksByAuthor_ShouldReturnBooksByAuthor() throws Exception {
        // Given
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        // When & Then
        mockMvc.perform(get("/books/author/{authorSurname}", "Толстой"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Война и мир"))
                .andExpect(jsonPath("$[1].title").value("Анна Каренина"));
    }

    @Test
    void getAverageRating_ShouldReturnCorrectAverage() throws Exception {
        // Given
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        // When & Then
        mockMvc.perform(get("/books/average-rating"))
                .andExpect(status().isOk())
                .andExpect(content().string("4.8")); // (4.8 + 4.9 + 4.7) / 3 = 4.8
    }

    @Test
    void getBooksGroupedByGenre_ShouldReturnGroupedBooks() throws Exception {
        // Given
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        // When & Then
        mockMvc.perform(get("/books/grouped-by-genre"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.Роман.length()").value(3));
    }

    @Test
    void findById_ShouldReturnBook() throws Exception {
        // Given
        Book savedBook = bookRepository.save(book1);

        // When & Then
        mockMvc.perform(get("/books/{id}", savedBook.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(savedBook.getId()))
                .andExpect(jsonPath("$.title").value("Война и мир"));
    }

    @Test
    void searchBooks_ShouldFindByTitleIgnoreCase() throws Exception {
        // Given
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        // When & Then
        mockMvc.perform(get("/books/search")
                        .param("keyword", "война"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Война и мир"));
    }

    @Test
    void searchBooks_ShouldFindByAuthorIgnoreCase() throws Exception {
        // Given
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        // When & Then
        mockMvc.perform(get("/books/search")
                        .param("keyword", "толстой"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getBookTitlesAsString_ShouldReturnCommaSeparatedTitles() throws Exception {
        // Given
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        // When & Then
        mockMvc.perform(get("/books/titles"))
                .andExpect(status().isOk())
                .andExpect(content().string("Война и мир, Преступление и наказание, Анна Каренина"));
    }

    @Test
    void getAuthorStatistics_ShouldReturnStatistics() throws Exception {
        // Given
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        // When & Then
        mockMvc.perform(get("/books/author-statistics")
                        .param("authorName", "Толстой"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bookCount").value(2))
                .andExpect(jsonPath("$.averageRating").value(4.75))
                .andExpect(jsonPath("$.favouriteGenre").value("Роман"));
    }

    @Test
    void removeBook_ShouldDeleteBook() throws Exception {
        // Given
        Book savedBook = bookRepository.save(book1);

        // When & Then
        mockMvc.perform(delete("/books/{id}", savedBook.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("Книга удалена (если существовала)"));

        // Verify
        assertThat(bookRepository.findById(savedBook.getId())).isEmpty();
    }

    // ==================== СЕРВИС ТЕСТЫ ====================

    @Test
    void bookService_FindBooksByYearRange_ShouldReturnBooksInRange() {
        // Given
        bookRepository.save(book1); // 1869
        bookRepository.save(book2); // 1866
        bookRepository.save(book3); // 1877

        // When
        List<Book> books = bookService.findBooksByYearRange(1865, 1870);

        // Then
        assertThat(books).hasSize(2);
        assertThat(books).extracting(Book::getTitle)
                .containsExactlyInAnyOrder("Война и мир", "Преступление и наказание");
    }

    @Test
    void bookService_SearchBooks_ShouldFindByPartialMatch() {
        // Given
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        // When
        List<Book> results = bookService.searchBooks("мир");

        // Then
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getTitle()).isEqualTo("Война и мир");
    }

    @Test
    void bookService_GetAuthorStatistics_ShouldCalculateCorrectStatistics() {
        // Given
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        // When
        Map<String, Serializable> statistics = bookService.getAuthorStatistics("Толстой");

        // Then
        assertThat(statistics.get("bookCount")).isEqualTo(2);
        assertThat((Double) statistics.get("averageRating")).isEqualTo(4.75);
        assertThat(statistics.get("favouriteGenre")).isEqualTo("Роман");
    }

    @Test
    void bookService_GetBooksGroupedByGenre_ShouldGroupCorrectly() {
        // Given
        Book detectiveBook = new Book();
        detectiveBook.setTitle("Шерлок Холмс");
        detectiveBook.setAuthor(author1);
        detectiveBook.setGenre(genre2);
        detectiveBook.setRating(4.5f);
        detectiveBook.setReleaseYear(1890);

        bookRepository.save(book1);
        bookRepository.save(detectiveBook);

        // When
        Map<String, List<Book>> grouped = bookService.getBooksGroupedByGenre();

        // Then
        assertThat(grouped).hasSize(2);
        assertThat(grouped.get("Роман")).hasSize(1);
        assertThat(grouped.get("Детектив")).hasSize(1);
        assertThat(grouped.get("Роман").get(0).getTitle()).isEqualTo("Война и мир");
        assertThat(grouped.get("Детектив").get(0).getTitle()).isEqualTo("Шерлок Холмс");
    }

    // ==================== РЕПОЗИТОРИ ТЕСТЫ ====================

    @Test
    void bookRepository_FindByAuthorSurname_ShouldReturnCorrectBooks() {
        // Given
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        // When
        List<Book> books = bookRepository.findByAuthorSurname("Толстой");

        // Then
        assertThat(books).hasSize(2);
        assertThat(books).extracting(Book::getTitle)
                .containsExactlyInAnyOrder("Война и мир", "Анна Каренина");
    }

    @Test
    void bookRepository_FindByReleaseYearBetween_ShouldReturnBooksInRange() {
        // Given
        bookRepository.save(book1); // 1869
        bookRepository.save(book2); // 1866
        bookRepository.save(book3); // 1877

        // When
        List<Book> books = bookRepository.findByReleaseYearBetween(1865, 1870);

        // Then
        assertThat(books).hasSize(2);
        assertThat(books).extracting(Book::getReleaseYear)
                .containsExactlyInAnyOrder(1869, 1866);
    }

    @Test
    void bookRepository_FindByRatingAndGenre_ShouldReturnFilteredBooks() {
        // Given
        bookRepository.save(book1); // rating 4.8
        bookRepository.save(book2); // rating 4.9
        bookRepository.save(book3); // rating 4.7

        // When
        List<Book> books = bookRepository.findByRatingAndGenre(4.8f, "Роман");

        // Then
        assertThat(books).hasSize(2);
        assertThat(books).extracting(Book::getTitle)
                .containsExactlyInAnyOrder("Война и мир", "Преступление и наказание");
    }

    @Test
    void bookRepository_SearchByTitleKeyword_ShouldFindByPartialMatch() {
        // Given
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        // When
        List<Book> books = bookRepository.searchByTitleKeyword("мир");

        // Then
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Война и мир");
    }

    @Test
    void bookRepository_FindByAuthorIdWithGraph_ShouldLoadAuthorAndGenre() {
        // Given
        Book savedBook = bookRepository.save(book1);

        // When
        List<Book> books = bookRepository.findByAuthorIdWithGraph(author1.getId());

        // Then
        assertThat(books).hasSize(1);
        Book foundBook = books.get(0);
        assertThat(foundBook.getAuthor()).isNotNull();
        assertThat(foundBook.getAuthor().getFirstName()).isEqualTo("Лев");
        assertThat(foundBook.getAuthor().getSurname()).isEqualTo("Толстой");
        assertThat(foundBook.getGenre()).isNotNull();
        assertThat(foundBook.getGenre().getName()).isEqualTo("Роман");
    }

    @Test
    void bookRepository_FindTopRatedAfterYear_ShouldReturnSortedBooks() {
        // Given
        bookRepository.save(book1); // 1869, rating 4.8
        bookRepository.save(book2); // 1866, rating 4.9
        bookRepository.save(book3); // 1877, rating 4.7

        // When
        List<Book> books = bookRepository.findTopRatedAfterYear(1865);

        // Then
        assertThat(books).hasSize(3);
        assertThat(books.get(0).getRating()).isEqualTo(4.9f);
        assertThat(books.get(1).getRating()).isEqualTo(4.8f);
        assertThat(books.get(2).getRating()).isEqualTo(4.7f);
    }

    @Test
    void bookRepository_FindBooksByAuthorAndPeriod_ShouldReturnFilteredBooks() {
        // Given
        bookRepository.save(book1); // 1869
        bookRepository.save(book3); // 1877

        // When
        List<Book> books = bookRepository.findBooksByAuthorAndPeriod(
                author1.getId(), 1865, 1870);

        // Then
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Война и мир");
    }

    @Test
    void bookRepository_FindByAuthorNotNull_ShouldReturnBooksWithAuthors() {
        // Given
        Book bookWithoutAuthor = new Book();
        bookWithoutAuthor.setTitle("Анонимная книга");
        bookWithoutAuthor.setGenre(genre1);
        bookWithoutAuthor.setRating(3.5f);
        bookWithoutAuthor.setReleaseYear(1900);
        // Не устанавливаем автора

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(bookWithoutAuthor);

        // When
        List<Book> books = bookRepository.findByAuthorNotNull();

        // Then
        assertThat(books).hasSize(2);
        assertThat(books).extracting(Book::getTitle)
                .containsExactlyInAnyOrder("Война и мир", "Преступление и наказание");
    }

    @Test
    void bookRepository_FindByAuthorIsNull_ShouldReturnBooksWithoutAuthors() {
        // Given
        Book bookWithoutAuthor = new Book();
        bookWithoutAuthor.setTitle("Анонимная книга");
        bookWithoutAuthor.setGenre(genre1);
        bookWithoutAuthor.setRating(3.5f);
        bookWithoutAuthor.setReleaseYear(1900);
        // Не устанавливаем автора

        bookRepository.save(book1);
        bookRepository.save(bookWithoutAuthor);

        // When
        List<Book> books = bookRepository.findByAuthorIsNull();

        // Then
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Анонимная книга");
    }

    @Test
    void bookService_GetBooksByFilter_ShouldFilterByMultipleCriteria() {
        // Given
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        BookFilter filter = new BookFilter() {
            @Override
            public boolean filter(Book book) {
                return book.getRating() >= 4.8f &&
                        book.getReleaseYear() >= 1865 &&
                        book.getAuthor().getSurname().equals("Толстой");
            }
        };

        // When
        List<Book> filtered = bookService.getBooksByFilter(filter);

        // Then
        assertThat(filtered).hasSize(1);
        assertThat(filtered.get(0).getTitle()).isEqualTo("Война и мир");
    }

    @Test
    void fullWorkflow_CreateFindUpdateDelete_ShouldWorkCorrectly() {
        // Given
        Book newBook = new Book();
        newBook.setTitle("Тестовая книга");
        newBook.setAuthor(author1);
        newBook.setGenre(genre1);
        newBook.setRating(5.0f);
        newBook.setReleaseYear(2023);

        // 1. Create
        bookService.addBook(newBook);
        List<Book> allBooks = bookService.getAllBooks();
        assertThat(allBooks).hasSize(1);

        Book savedBook = allBooks.get(0);
        Integer bookId = savedBook.getId();

        // 2. Find
        Book foundBook = bookService.findById(bookId);
        assertThat(foundBook.getTitle()).isEqualTo("Тестовая книга");

        // 3. Search
        List<Book> searchResults = bookService.searchBooks("тестовая");
        assertThat(searchResults).hasSize(1);

        // 4. Update (через репозиторий напрямую)
        savedBook.setRating(4.5f);
        bookRepository.save(savedBook);

        // 5. Verify update
        Book updatedBook = bookService.findById(bookId);
        assertThat(updatedBook.getRating()).isEqualTo(4.5f);

        // 6. Delete
        bookService.removeBook(bookId);

        // 7. Verify deletion
        assertThat(bookRepository.findById(bookId)).isEmpty();
    }
}