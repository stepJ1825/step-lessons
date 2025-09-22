package by.step.service;

import by.step.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthors();

    void addAuthor(Author author);

}
