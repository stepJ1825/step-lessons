package by.step.controller;

import by.step.entity.Author;
import by.step.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorRestController {

    private final AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAuthors();
    }
}