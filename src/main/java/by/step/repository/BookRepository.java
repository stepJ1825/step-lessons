package by.step.repository;

import by.step.model.simple.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAllBooks();

    Book findById(int id);

    void addBook(Book book);

    void removeBook(int id);

    List<Book> findBooksByAuthor(String author);

    List<Book> findBooksByYearRange(int start, int end);

    void updateAllBooks(List<Book> books);

    void updateAllBooksWithNamedParams(List<Book> books);
}
