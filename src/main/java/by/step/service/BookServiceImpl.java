package by.step.service;

import by.step.model.Book;
import by.step.repository.BookRepository;
import by.step.repository.BookRepositoryImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {

    private final BookRepository repository = new BookRepositoryImpl();

    @Override
    public void addBook(Book book) {
        repository.addBook(book);
    }

    @Override
    public void removeBook(int id) {
        repository.removeBook(id);
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return repository.findBooksByAuthor(author);
    }

    @Override
    public List<Book> findBooksByYearRange(int start, int end) {
        return repository.findBooksByYearRange(start, end);
    }

    @Override
    public List<Book> getAllBooks() {
        return repository.getAllBooks();
    }

    @Override
    public List<Book> getBooksByFilter(BookFilter bookFilter) {
        return repository.getAllBooks()
                .stream().filter(bookFilter::filter)
                .toList();
    }

    @Override
    public float getAverageRating() {
        return (float) repository.getAllBooks().stream()
                .mapToDouble(Book::getRating)
                .average()
                .orElse(0D);
    }

    @Override
    public Map<String, List<Book>> getBooksGroupedByGenre() {
        return repository.getAllBooks().stream()
                .collect(Collectors.groupingBy(book ->
                        book.getGenre().getName()));
    }

    @Override
    public Book findById(int id) {
        return repository.findById(id);
    }
}
