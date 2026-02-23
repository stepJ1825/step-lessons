package by.step.repository;

import by.step.model.simple.Author;

import java.util.List;

public interface AuthorRepository {
    void saveAuthor(Author author);

    Author getById(int id);

    List<Author> getAuthors();

    void removeAuthor(int id);

}
