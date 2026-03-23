package by.step.service.impl;

import by.step.entity.Author;
import by.step.repository.AuthorRepository;
import by.step.service.BookHibernateService;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl service;

    @Test
    void getAuthors() throws NoSuchMethodException {
        when(authorRepository.findAll())
                .thenReturn(List.of(new Author(), new Author()));
        List<Author> authors = service.getAuthors();
        Assertions.assertThat(authors).isNotEmpty();

        Method getAuthorList = service.getClass().getDeclaredMethod("getAuthorList");
        getAuthorList.setAccessible(true);
        //TODO: тест приватного метода в Мокито
    }

    @Test
    void addAuthor() {
        //TODO: написать тест
    }
}