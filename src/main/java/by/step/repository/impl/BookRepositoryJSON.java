package by.step.repository.impl;

import by.step.model.Book;
import by.step.repository.BookRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class BookRepositoryJSON implements BookRepository {

    @Setter
    private String data;
    @Setter
    private SimpleDateFormat df;


    @Override
    public List<Book> getAllBooks() {
        try {
            Thread.sleep(5000L);
            return newMapper().readValue(new File(data),
                    new TypeReference<>() {
                    });
        } catch (IOException|InterruptedException e) {
            throw new RuntimeException(e); //TODO: реализовать функционал при отсутствии файла
        }
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
        List<Book> allBooks = getAllBooks();
        int nextId;
        if (allBooks.isEmpty()) {
            nextId = 1;
        } else {
            nextId = allBooks.get(allBooks.size() - 1).getId() + 1;
        }
        book.setId(nextId);
        allBooks.add(book);
        rewriteData(allBooks);
    }

    @Override
    public void removeBook(int id) {
        List<Book> allBooks = getAllBooks();
        allBooks.remove(findById(id));
        rewriteData(allBooks);
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

    private void rewriteData(List<Book> books) {
        try {
            newMapper().writeValue(new File(data), books);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ObjectMapper newMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(df);
        mapper.setLocale(Locale.ENGLISH);
        mapper.registerModule(new JSR310Module());
        return mapper;
    }
}
