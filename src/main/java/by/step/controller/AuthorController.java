package by.step.controller;

import by.step.model.Author;
import by.step.service.AuthorService;
import by.step.service.BookService;
import by.step.service.impl.AuthorServiceImpl;
import by.step.service.impl.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController() {
        this.authorService = new AuthorServiceImpl();
    }

}
