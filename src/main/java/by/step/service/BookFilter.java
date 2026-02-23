package by.step.service;

import by.step.model.simple.Book;

@FunctionalInterface
public interface BookFilter {
    boolean filter(Book book);

    default BookFilter and(BookFilter other){
        return book -> this.filter(book) && other.filter(book);
    }

    default BookFilter or(BookFilter other){
        return book -> this.filter(book) || other.filter(book);
    }

}
