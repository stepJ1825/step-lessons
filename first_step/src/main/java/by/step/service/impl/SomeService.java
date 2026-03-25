package by.step.service.impl;

import by.step.entity.Author;
import by.step.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SomeService {
    private final AuthorService authorService;

    public void addAuthor(Author author){
        authorService.addAuthor(author);
    }
}
