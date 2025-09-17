package by.step.repository;

import by.step.model.Author;
import by.step.model.Book;
import by.step.util.Util;

import java.util.List;
import java.util.stream.Collectors;

public class BookRepositoryJSON implements BookRepository {
    @Override
    public List<Book> getAllBooks() {
        return Util.getBooks();
    }

    @Override
    public Book findById(int id) {
        return getAllBooks()
                .stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(new Book());
    }

    @Override
    public void addBook(Book book) {
        throw new IllegalStateException(); //TODO
    }

    @Override
    public void removeBook(int id) {
        throw new IllegalStateException(); //TODO
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return getAllBooks().stream()
                .filter(book ->
                        (book.getAuthor().getFirstName().toUpperCase()
                                + " "
                                + book.getAuthor().getSurname().toUpperCase())
                                .contains(author.toUpperCase())
                )
                .toList();
    }

    @Override
    public List<Book> findBooksByYearRange(int start, int end) {
        return getAllBooks()
                .stream()
                .filter(book -> book.getYear() < end
                        && book.getYear() > start)
                .collect(Collectors.toList());
    }
}
