package by.step.repository;

import by.step.model.Author;

import java.util.List;

public interface AuthorRepository {
    List<Author> getAuthors();

    void addAuthor(Author author);
}
