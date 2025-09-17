package by.step;

import by.step.model.Author;
import by.step.model.Book;
import by.step.model.Genre;
import by.step.service.BookFilter;
import by.step.service.BookService;
import by.step.service.BookServiceImpl;
import by.step.util.BookFilterUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Создание библиотеки
        BookService service = new BookServiceImpl();

        String title = "ocean";
        BookFilter oceanFilter = BookFilterUtil.getByTitle(title);
        List<Book> booksByFilter = service.getBooksByFilter(oceanFilter);

        String word2 = "miller";
        BookFilter byKeyword = BookFilterUtil.getByKeyword(word2);
        List<Book> booksByKeyword = service.getBooksByFilter(byKeyword);

        service.addBook(new Book("123",new Author(1,"a","b"),new Genre(1,"dwhaj"),2025, 10));


        System.out.println();


    }


}

