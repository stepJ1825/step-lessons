package by.step.repository;

import by.step.exception.DaoException;
import by.step.model.Book;
import by.step.service.BookFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BookRepositoryImpl implements BookRepository {

    private final List<Book> library = initRepository();

    private List<Book> initRepository() {
        List<Book> localLibrary = new ArrayList<>();
        List<Book> books = TestDataGenerator.generateRandomBooks(50);
        List<Book> sampleBooks = TestDataGenerator.generateSampleBooks();
        books.addAll(sampleBooks);
        IntStream.range(0, books.size())
                .forEach(index -> {
                    Book book = books.get(index);
                    book.setId(index + 1);
                    localLibrary.add(book);
                });

        return localLibrary;
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(library); //возвращать копию хранилища
    }

    @Override
    public Book findById(int id) {
        List<Book> collect = library.stream()
                .filter(book -> book.getId() == id)
                .collect(Collectors.toList());
        if (collect.size() > 1) {
            throw new DaoException();
        }
        return collect.get(0);
    }

    @Override
    public void addBook(Book book) {
        int nextId;
        if (library.isEmpty()) {
            nextId = 1;
        } else {
            nextId = library.get(library.size() - 1).getId() + 1;
        }
        book.setId(nextId);
        boolean isBookAdded = library.add(book);
        if (!isBookAdded) {
            throw new IllegalArgumentException("Книга не добавлена");
        }
    }

    @Override
    public void removeBook(int id) {
        Book remove = library.remove(id);
        if (remove == null) {
            throw new IllegalArgumentException("Ошибка при удалении.");
        }
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return library.stream()
                .filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findBooksByYearRange(int start, int end) {
        return library.stream()
                .filter(book -> book.getYear() < end
                        && book.getYear() > start)
                .collect(Collectors.toList());
    }
}
