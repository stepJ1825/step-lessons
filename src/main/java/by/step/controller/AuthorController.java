package by.step.controller;

import by.step.model.Author;
import by.step.service.AuthorService;
import by.step.service.BookService;
import by.step.service.impl.AuthorServiceImpl;
import by.step.service.impl.BookServiceImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class AuthorController {

    private final AuthorService authorService;
    private Scanner scanner;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // Метод инициализации — вызывается Spring после создания бина
    public void init() {
        this.scanner = new Scanner(System.in);
    }

    public void start(){
        List<Author> authors = authorService.getAuthors();
        System.out.println(authors);
    }
}
