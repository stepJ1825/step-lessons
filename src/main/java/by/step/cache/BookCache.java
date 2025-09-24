package by.step.cache;

import by.step.model.Book;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.*;

public class BookCache {
    private Date creationDate = new Date();
    //    private final List<Book> library = new ArrayList<>();
    private final List<WeakReference<Book>> library = new ArrayList<>();

    public List<Book> getAllBooks() {
        Date readAllBooksDate = new Date();
        long timeFromStart = readAllBooksDate.getTime() - creationDate.getTime();
        if (timeFromStart < 6000L) {
            return library.stream()
                    .map(Reference::get)
                    .toList();
        } else return new ArrayList<>();
    }

    public void fillCache(List<Book> books) {
        books.forEach(book -> library.add(new WeakReference<>(book)));
        creationDate = new Date();
        //например  21:22:22 - всё ОК.
        //в 21:23:22 - кэш невалидный, надо чистить
    }

}
