package by.step.service.impl;

import by.step.entity.Author;
import by.step.repository.AuthorRepository;
import by.step.service.AuthorService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplMockitoTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl service;

    @Test
    void getAuthors_returnsRepositoryResult() {
        List<Author> authors = List.of(
                Author.builder().id(1).firstName("A").surname("Brown").build(),
                Author.builder().id(2).firstName("B").surname("Smith").build()
        );

        when(authorRepository.findAll()).thenReturn(authors);

        Assertions.assertThat(service.getAuthors()).isEqualTo(authors);
        verify(authorRepository).findAll();
    }

    @Test
    void addAuthor_savesEntity() {
        Author author = Author.builder().id(10).firstName("Test").surname("Surname").build();

        service.addAuthor(author);

        verify(authorRepository, times(1)).save(author);
    }
}

