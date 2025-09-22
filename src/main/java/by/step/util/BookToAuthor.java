package by.step.util;

import by.step.model.Book;
import by.step.service.BookService;
import by.step.service.impl.AuthorServiceImpl;
import by.step.service.impl.BookServiceImpl;

import java.util.List;

public class BookToAuthor {
    public static void main(String[] args) {
        AuthorServiceImpl authorService = new AuthorServiceImpl();
        BookService bookService = new BookServiceImpl();
        List<Book> allBooks = bookService.getAllBooks();

        allBooks.stream()
                .map(Book::getAuthor)
                .distinct()
                .forEach(authorService::addAuthor);
    }
}
