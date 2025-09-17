package by.step.service;

import by.step.model.Book;
import by.step.repository.BookRepository;
import by.step.repository.BookRepositoryJSON;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {

    private final BookRepository repository = new BookRepositoryJSON();

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

    @Override
    public List<Book> searchBooks(String keyword) {
        String upperCase = keyword.toUpperCase();
        return getAllBooks()
                .stream()
                .filter(book -> book.getTitle().toUpperCase().contains(upperCase)
                        || book.getAuthor().getFirstName().toUpperCase().contains(upperCase)
                        || book.getAuthor().getSurname().toUpperCase().contains(upperCase))
//                .filter(book -> book.toString().toUpperCase().contains(upperCase))
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
    public Map<String, Serializable> getAuthorStatistics(String author) {
        List<Book> booksByAuthor = repository.findBooksByAuthor(author);
        double averageRating = booksByAuthor.stream()
                .mapToDouble(Book::getRating)
                .average()
                .orElse(0);
        int bookCount = booksByAuthor.size();

        String favouriteGenre = booksByAuthor.stream()
                .collect(Collectors.groupingBy(book ->
                        book.getGenre().getName(), Collectors.counting()))
                .entrySet().stream()
                .max((o1, o2) ->
                        Math.toIntExact(o1.getValue() - o2.getValue()))
                .get()
                .getKey();


        //THRILLER - 3, COMEDY - 1,  FANTASY - 2

        return Map.of("averageRating", averageRating,
                "bookCount", bookCount,
                "favouriteGenre", favouriteGenre);
    }
}
