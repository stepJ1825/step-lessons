package by.step.cache;

import by.step.model.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class BookCache {
    private Date creationDate = new Date();
    private final List<Book> library = new ArrayList<>();

    public List<Book> getAllBooks() {
        Date readAllBooksDate = new Date();
        long timeFromStart = readAllBooksDate.getTime() - creationDate.getTime();
        if (timeFromStart < 6000L) {
            return new ArrayList<>(library);
        } else return new ArrayList<>();
    }

    public void fillCache(List<Book> books) {
        library.addAll(books);
        creationDate = new Date();
        //например  21:22:22 - всё ОК.
        //в 21:23:22 - кэш невалидный, надо чистить
    }

}
