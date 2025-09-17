package by.step.service;

import by.step.model.Book;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BookService {
    void addBook(Book book);

    void removeBook(int id);

    List<Book> findBooksByAuthor(String author);

    List<Book> findBooksByYearRange(int start, int end);

    List<Book> getAllBooks();

    List<Book> getBooksByFilter(BookFilter filter);

    float getAverageRating();

    Map<String, List<Book>> getBooksGroupedByGenre();

    Book findById(int id);

    List<Book> searchBooks(String keyword); //поиск по названию и автору (игнорируя регистр)

    String getBookTitlesAsString(); // все названия книг в виде строки через запятую

    Map<String, Serializable> getAuthorStatistics(String author); //статистика по авторам (количество книг каждого автора)

}
