package by.step.repository.impl;

import by.step.bpp.Auditing;
import by.step.bpp.InjectBean;
import by.step.bpp.MyTransaction;
import by.step.model.Book;
import by.step.repository.BookRepository;
import by.step.repository.db.ConnectionPool;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.annotation.Resources;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@MyTransaction
@Auditing
@Repository("bookRepository")
public class BookRepositoryJSON implements BookRepository {

    @Setter
    @Value("#{'${data.json}'.split(',')[0]}")
    private String data;

    @Setter
    @Value("${app.date.format}")
    private String dateFormatPattern;

    private SimpleDateFormat df;

    @PostConstruct
    private void initDf(){
        df = new SimpleDateFormat(dateFormatPattern);
    }

    private BookRepositoryJSON() {
    }

    @InjectBean
    //    @Autowired(required = false) + @Qualifier(value = "pool1")
    //    @Resource(name = "pool1")
    //    @Autowired
    //    @Qualifier(value = "by.step.repository.db.ConnectionPool#1")
    private ConnectionPool connectionPool;

    @Autowired
    private List<ConnectionPool> pools; // внедрение всех соответствующих бинов в коллекцию

    @Override
    public List<Book> getAllBooks() {
        try {
            Thread.sleep(5000L);
            return newMapper().readValue(
                    new File(data),
                    new TypeReference<>() {
                    }
            );
        } catch (IOException | InterruptedException e) {
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
