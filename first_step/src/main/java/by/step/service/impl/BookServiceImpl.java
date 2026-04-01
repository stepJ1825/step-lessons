package by.step.service.impl;

import by.step.entity.Book;
import by.step.repository.BookRepository;
import by.step.service.AuthorService;
import by.step.service.BookFilter;
import by.step.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final AuthorService authorService;

    @Override
    public Book addBook(Book book) {
        authorService.validateAuthor(book.getAuthor());
        return repository.save(book);
    }

    @Override
    public void removeBook(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<Book> findBooksByAuthor(String surname) {
        return repository.findByAuthorSurname(surname);
    }

    @Override
    public List<Book> findBooksByYearRange(int start, int end) {
        return repository.findByReleaseYearBetween(start, end);
    }

    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public List<Book> getBooksByFilter(BookFilter bookFilter) {
        return repository.findAll()
                         .stream().filter(bookFilter::filter)
                         .toList();
    }

    @Override
    public float getAverageRating() {
        return (float) repository.findAll().stream()
                                 .mapToDouble(Book::getRating)
                                 .average()
                                 .orElse(0D);
    }

    @Override
    public Map<String, List<Book>> getBooksGroupedByGenre() {
        return repository.findAll().stream()
                         .collect(Collectors.groupingBy(book ->
                                 book.getGenre().getName()));
    }

    @Override
    public Book findById(int id) {
        return repository.findById(id).orElse(new Book());
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        String upperCase = keyword.toUpperCase();
        return getAllBooks()
                .stream()
                .filter(book -> book.getTitle().toUpperCase().contains(upperCase)
                                || book.getAuthor().getFirstName().toUpperCase().contains(upperCase)
                                || book.getAuthor().getSurname().toUpperCase().contains(upperCase))
                .toList();
    }

    /**
     * @return Все названия книг в виде строки через запятую
     */
    @Override
    public String getBookTitlesAsString() {
        return getAllBooks().stream()
                            .map(Book::getTitle)
                            .collect(Collectors.joining(", "));
    }

    @Override
    public Map<String, Serializable> getAuthorStatistics(String surname) {
        List<Book> booksByAuthor = repository.findByAuthorSurname(surname);
        double averageRating = getAverageRating(booksByAuthor);
        int bookCount = booksByAuthor.size();

        String favouriteGenre = booksByAuthor.stream()
                                             .collect(Collectors.groupingBy(
                                                     book ->
                                                             book.getGenre().getName(), Collectors.counting()
                                             ))
                                             .entrySet().stream()
                                             .max((o1, o2) ->
                                                     Math.toIntExact(o1.getValue() - o2.getValue()))
                                             .orElseThrow(NoSuchElementException::new)
                                             .getKey();
        return Map.of(
                "averageRating", averageRating,
                "bookCount", bookCount,
                "favouriteGenre", favouriteGenre
        );
    }

    private double getAverageRating(List<Book> booksByAuthor) {
        double averageRating = booksByAuthor.stream()
                                            .mapToDouble(Book::getRating)
                                            .average()
                                            .orElse(0);
        return averageRating;
    }

    @Override
    public Book updateBook(Book existingBook) {
        return repository.save(existingBook);
    }
}
