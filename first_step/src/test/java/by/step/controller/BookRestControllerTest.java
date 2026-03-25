package by.step.controller;

import by.step.entity.Author;
import by.step.entity.Book;
import by.step.entity.Genre;
import by.step.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.Serializable;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookRestController.class)
class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    private Book testBook1;
    private Book testBook2;
    private List<Book> testBooks;

    @BeforeEach
    void setUp() {
        testBook1 = Book.builder()
                .id(1)
                .title("Война и мир")
                .author(Author.builder().firstName("Лев").surname("Толстой").build())
                .genre(Genre.builder().name("Роман").build())
                .rating(4.8f)
                .build();

        testBook2 = Book.builder()
                .id(2)
                .title("Преступление и наказание")
                .author(Author.builder().firstName("Фёдор").surname("Достоевский").build())
                .genre(Genre.builder().name("Роман").build())
                .rating(4.9f)
                .build();

        testBooks = Arrays.asList(testBook1, testBook2);
    }

    @Test
    void addBook_ShouldReturnSuccessMessage() throws Exception {
        // Given
        Book newBook = Book.builder()
                .title("Анна Каренина")
                .author(Author.builder().firstName("Лев").surname("Толстой").build())
                .genre(Genre.builder().name("Роман").build())
                .rating(4.7f)
                .build();

        // doNothing().when(bookService).addBook(any(Book.class));

        // When & Then
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newBook)))
                .andExpect(status().isOk())
                .andExpect(content().string("Книга успешно добавлена!"));

        verify(bookService, times(1)).addBook(any(Book.class));
    }

    @Test
    void removeBook_ShouldReturnSuccessMessage() throws Exception {
        // Given
        int bookId = 1;
//        doNothing().when(bookService).removeBook(bookId);

        // When & Then
        mockMvc.perform(delete("/books/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(content().string("Книга удалена (если существовала)"));

        verify(bookService, times(1)).removeBook(bookId);
    }

    @Test
    void getAllBooks_ShouldReturnListOfBooks() throws Exception {
        // Given
        when(bookService.getAllBooks()).thenReturn(testBooks);

        // When & Then
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Война и мир"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Преступление и наказание"));

        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    void findBooksByAuthor_ShouldReturnBooksByAuthor() throws Exception {
        // Given
        String authorSurname = "Толстой";
        List<Book> authorBooks = Collections.singletonList(testBook1);
        when(bookService.findBooksByAuthor(authorSurname)).thenReturn(authorBooks);

        // When & Then
        mockMvc.perform(get("/books/author/{authorSurname}", authorSurname))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].author.firstName").value("Лев"))
                .andExpect(jsonPath("$[0].author.surname").value("Толстой"));

        verify(bookService, times(1)).findBooksByAuthor(authorSurname);
    }

    @Test
    void getAverageRating_ShouldReturnAverageRating() throws Exception {
        // Given
        float averageRating = 4.85f;
        when(bookService.getAverageRating()).thenReturn(averageRating);

        // When & Then
        mockMvc.perform(get("/books/average-rating"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(averageRating)));

        verify(bookService, times(1)).getAverageRating();
    }

    @Test
    void getBooksGroupedByGenre_ShouldReturnGroupedBooks() throws Exception {
        // Given
        Map<String, List<Book>> groupedBooks = new HashMap<>();
        groupedBooks.put("Роман", testBooks);

        when(bookService.getBooksGroupedByGenre()).thenReturn(groupedBooks);

        // When & Then
        mockMvc.perform(get("/books/grouped-by-genre"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.Роман.length()").value(2));

        verify(bookService, times(1)).getBooksGroupedByGenre();
    }

    @Test
    void findById_WithValidId_ShouldReturnBook() throws Exception {
        // Given
        int bookId = 1;
        when(bookService.findById(bookId)).thenReturn(testBook1);

        // When & Then
        mockMvc.perform(get("/books/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Война и мир"))
                .andExpect(jsonPath("$.author.firstName").value("Лев"))
                .andExpect(jsonPath("$.author.surname").value("Толстой"));

        verify(bookService, times(1)).findById(bookId);
    }

    @Test
    void findByIdInParam_WithValidId_ShouldReturnBook() throws Exception {
        // Given
        int bookId = 1;
        when(bookService.findById(bookId)).thenReturn(testBook1);

        // When & Then
        mockMvc.perform(get("/books/param-id")
                        .param("id", String.valueOf(bookId)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Война и мир"));

        verify(bookService, times(1)).findById(bookId);
    }

    @Test
    void findByIdInParam_WithInValidId_ShouldReturn4xx() throws Exception {
        // Given
        int bookId = 10000;

        // When & Then
        mockMvc.perform(get("/books/{id}", bookId))
                .andExpect(status().is4xxClientError());

        verify(bookService, never()).findById(bookId);
    }

    @Test
    void findByIdInParam_WithNullId_ShouldReturnNull() throws Exception {
        // Given
        // No id parameter provided

        // When & Then
        mockMvc.perform(get("/books/param-id"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(bookService, never()).findById(anyInt());
    }

    @Test
    void searchBooks_ShouldReturnBooksByKeyword() throws Exception {
        // Given
        String keyword = "война";
        List<Book> searchResults = Collections.singletonList(testBook1);
        when(bookService.searchBooks(keyword)).thenReturn(searchResults);

        // When & Then
        mockMvc.perform(get("/books/search")
                        .param("keyword", keyword))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Война и мир"));

        verify(bookService, times(1)).searchBooks(keyword);
    }

    @Test
    void getBookTitlesAsString_ShouldReturnTitlesString() throws Exception {
        // Given
        String titlesString = "Война и мир, Преступление и наказание";
        when(bookService.getBookTitlesAsString()).thenReturn(titlesString);

        // When & Then
        mockMvc.perform(get("/books/titles"))
                .andExpect(status().isOk())
                .andExpect(content().string(titlesString));

        verify(bookService, times(1)).getBookTitlesAsString();
    }

    @Test
    void getAuthorStatistics_ShouldReturnAuthorStatistics() throws Exception {
        // Given
        String authorName = "Лев Толстой";
        Map<String, Serializable> statistics = new HashMap<>();
        statistics.put("bookCount", 5);
        statistics.put("averageRating", 4.8f);

        when(bookService.getAuthorStatistics(authorName)).thenReturn(statistics);

        // When & Then
        mockMvc.perform(get("/books/author-statistics")
                        .param("authorName", authorName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bookCount").value(5))
                .andExpect(jsonPath("$.averageRating").value(4.8));

        verify(bookService, times(1)).getAuthorStatistics(authorName);
    }
}