package by.step.service.impl;

import by.step.entity.Author;
import by.step.entity.Book;
import by.step.entity.Genre;
import by.step.repository.BookRepository;
import by.step.service.AuthorService;
import by.step.service.BookFilter;
import by.step.service.BookService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplMockitoTest {

    @Mock
    private BookRepository repository;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private BookServiceImpl service;

    @Test
    void addBook_savesEntity() {
        Book book = Book.builder()
                        .title("t1")
                        .releaseYear(2020)
                        .rating(4.2f)
                        .build();

        service.addBook(book);

        verify(repository, times(1)).save(book);
    }

    @Test
    void removeBook_deletesById() {
        service.removeBook(10);

        verify(repository, times(1)).deleteById(10);
    }

    @Test
    void findBooksByAuthor_returnsRepositoryResult() {
        Author author = Author.builder().id(102).firstName("Michael").surname("Brown").build();
        List<Book> books = List.of(
                Book.builder().id(1).title("b1").author(author).build(),
                Book.builder().id(2).title("b2").author(author).build()
        );

        when(repository.findByAuthorSurname("Brown")).thenReturn(books);

        List<Book> result = service.findBooksByAuthor("Brown");

        Assertions.assertThat(result).isEqualTo(books);
        verify(repository).findByAuthorSurname("Brown");
    }

    @Test
    void findBooksByYearRange_returnsRepositoryResult() {
        List<Book> books = List.of(
                Book.builder().id(1).title("b1").releaseYear(2019).build(),
                Book.builder().id(2).title("b2").releaseYear(2020).build()
        );

        when(repository.findByReleaseYearBetween(2019, 2020)).thenReturn(books);

        List<Book> result = service.findBooksByYearRange(2019, 2020);

        Assertions.assertThat(result).isEqualTo(books);
        verify(repository).findByReleaseYearBetween(2019, 2020);
    }

    @Test
    void getAllBooks_delegatesToRepository() {
        List<Book> books = List.of(Book.builder().id(1).title("b1").build());
        when(repository.findAll()).thenReturn(books);

        Assertions.assertThat(service.getAllBooks()).isEqualTo(books);
        verify(repository).findAll();
    }

    @Test
    void getBooksByFilter_filtersRepositoryBooks() {
        BookFilter filter = book -> book.getRating() >= 4.3f;
        List<Book> books = List.of(
                Book.builder().id(1).title("b1").rating(4.2f).build(),
                Book.builder().id(2).title("b2").rating(4.3f).build(),
                Book.builder().id(3).title("b3").rating(4.8f).build()
        );
        when(repository.findAll()).thenReturn(books);

        List<Book> result = service.getBooksByFilter(filter);

        Assertions.assertThat(result)
                  .extracting(Book::getId)
                  .containsExactly(2, 3);
    }

    @Test
    void getAverageRating_returnsAverageOrZero() {
        when(repository.findAll()).thenReturn(List.of(
                Book.builder().rating(4.0f).build(),
                Book.builder().rating(6.0f).build()
        ));

        Assertions.assertThat(service.getAverageRating()).isEqualTo(5.0f);
    }

    @Test
    void getAverageRating_emptyList_returnsZero() {
        when(repository.findAll()).thenReturn(List.of());

        Assertions.assertThat(service.getAverageRating()).isEqualTo(0.0f);
    }

    @Test
    void getBooksGroupedByGenre_groupsByGenreName() {
        Genre g1 = Genre.builder().id(1).name("FICTION").build();
        Genre g2 = Genre.builder().id(2).name("ROMANCE").build();

        List<Book> books = List.of(
                Book.builder().id(1).genre(g1).build(),
                Book.builder().id(2).genre(g1).build(),
                Book.builder().id(3).genre(g2).build()
        );
        when(repository.findAll()).thenReturn(books);

        Map<String, List<Book>> grouped = service.getBooksGroupedByGenre();

        Assertions.assertThat(grouped).hasSize(2);
        Assertions.assertThat(grouped.get("FICTION")).hasSize(2);
        Assertions.assertThat(grouped.get("ROMANCE")).hasSize(1);
    }

    @Test
    void findById_whenPresent_returnsEntity() {
        Book book = Book.builder().id(7).title("b7").build();
        when(repository.findById(7)).thenReturn(Optional.of(book));

        Book result = service.findById(7);

        Assertions.assertThat(result).isSameAs(book);
    }

    @Test
    void findById_whenAbsent_returnsNewBook() {
        when(repository.findById(999)).thenReturn(Optional.empty());

        Book result = service.findById(999);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTitle()).isNull();
    }

    @Test
    void searchBooks_matchesTitleOrAuthorNames_caseInsensitive() {
        Author oceanAuthorSurname = Author.builder().id(1).firstName("A").surname("Oceanista").build();
        Author oceanAuthorFirst = Author.builder().id(2).firstName("OceanMan").surname("X").build();
        Author other = Author.builder().id(3).firstName("Bob").surname("Y").build();

        List<Book> books = List.of(
                Book.builder().id(1).title("The Silent Ocean").author(other).build(),
                Book.builder().id(2).title("Something").author(oceanAuthorFirst).build(),
                Book.builder().id(3).title("Nothing").author(oceanAuthorSurname).build(),
                Book.builder().id(4).title("Other title").author(other).build()
        );
        when(repository.findAll()).thenReturn(books);

        List<Book> result = service.searchBooks("ocean");

        Assertions.assertThat(result).extracting(Book::getId).containsExactlyInAnyOrder(1, 2, 3);
    }

    @Test
    void getBookTitlesAsString_joinsWithCommaSpace() {
        when(repository.findAll()).thenReturn(List.of(
                Book.builder().title("Title1").build(),
                Book.builder().title("Title2").build()
        ));

        Assertions.assertThat(service.getBookTitlesAsString()).isEqualTo("Title1, Title2");
    }

    @Test
    void getAuthorStatistics_returnsAverageCountAndMostFrequentGenre() {
        Genre g1 = Genre.builder().id(1).name("FICTION").build();
        Genre g2 = Genre.builder().id(2).name("ROMANCE").build();
        Author author = Author.builder().id(102).firstName("Michael").surname("Brown").build();

        List<Book> booksByAuthor = List.of(
                Book.builder().id(1).author(author).genre(g1).rating(4.0f).build(),
                Book.builder().id(2).author(author).genre(g1).rating(5.0f).build(),
                Book.builder().id(3).author(author).genre(g2).rating(3.0f).build()
        );

        when(repository.findByAuthorSurname("Brown")).thenReturn(booksByAuthor);

        Map<String, Serializable> stats = service.getAuthorStatistics("Brown");

        Assertions.assertThat(stats.get("bookCount")).isEqualTo(3);
        Assertions.assertThat(stats.get("averageRating")).isEqualTo(4.0d);
        Assertions.assertThat(stats.get("favouriteGenre")).isEqualTo("FICTION");
    }
}

