package by.step.controller;

import by.step.model.Author;
import by.step.service.AuthorService;
import by.step.service.BookService;
import by.step.service.impl.AuthorServiceImpl;
import by.step.service.impl.BookServiceImpl;

import java.util.List;
import java.util.Scanner;

public class AuthorController {

    private final AuthorService authorService;
    private final Scanner scanner;

    public AuthorController() {
        this.authorService = new AuthorServiceImpl();
        this.scanner = new Scanner(System.in);
    }

    public void start(){
        List<Author> authors = authorService.getAuthors();
        System.out.println(authors);
    }
}
