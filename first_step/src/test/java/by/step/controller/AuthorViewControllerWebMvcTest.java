package by.step.controller;

import by.step.controller.author.AuthorViewController;
import by.step.entity.Author;
import by.step.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthorViewController.class)
class AuthorViewControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthorService authorService;

    private Author author1;
    private Author author2;
    private List<Author> authors;

    @BeforeEach
    void setUp() {
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

        authors = Arrays.asList(author1, author2);
    }

    @Test
    void getAllAuthors_ShouldReturnAuthorsListView() throws Exception {
        // Given
        when(authorService.getAuthors()).thenReturn(authors);

        // When & Then
        mockMvc.perform(get("/authors/list"))
               .andExpect(status().isOk())
               .andExpect(view().name("authors/list"))
               .andExpect(model().attributeExists("authors", "pageTitle", "students"))
               .andExpect(model().attribute("authors", authors))
               .andExpect(model().attribute("pageTitle", "Список авторов"));

        verify(authorService, times(1)).getAuthors();
    }

    @Test
    void getAllAuthorsView_ShouldReturnAuthorsListView() throws Exception {
        // Given
        when(authorService.getAuthors()).thenReturn(authors);

        // When & Then
        mockMvc.perform(get("/authors/list2"))
               .andExpect(status().isOk())
               .andExpect(view().name("authors/list"))
               .andExpect(model().attributeExists("authors", "pageTitle"))
               .andExpect(model().attribute("authors", authors))
               .andExpect(model().attribute("pageTitle", "Список авторов (ModelAndView)"));

        verify(authorService, times(1)).getAuthors();
    }
}