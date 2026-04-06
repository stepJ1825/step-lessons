package by.step.controller;

import by.step.controller.book.BookMapperRestController;
import by.step.dto.BookCreateDTO;
import by.step.entity.Author;
import by.step.entity.Book;
import by.step.entity.Genre;
import by.step.mapper.BookMapper;
import by.step.mapper.BookMapperImpl;
import by.step.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookMapperRestController.class)
class BookMapperRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    private BookMapper bookMapper = new BookMapperImpl(); // TODO: INJECT

    private BookMapperRestController bookMapperRestController =
            new BookMapperRestController(bookService,bookMapper);

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
        BookCreateDTO newBook = BookCreateDTO.builder()
                .title("Анна Каренина")
                .authorId(1)
                .genreId(10)
                .rating(4.7f)
                .build();

        // When & Then
        mockMvc.perform(post("/mapped/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newBook)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(newBook)));

        verify(bookService, times(1)).addBook(any(Book.class));
    }

}