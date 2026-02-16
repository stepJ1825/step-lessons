package by.step.repository.impl;

import by.step.model.Author;
import by.step.repository.AuthorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
    @Override
    public List<Author> getAuthors() {
        throw new RuntimeException("Not implemented"); //TODO: native JDBC
    }

    @Override
    public void saveAuthor(Author author) {
        throw new RuntimeException("Not implemented"); //TODO: native JDBC
    }

    @Override
    public Author getById(int id) {
        throw new RuntimeException("Not implemented"); //TODO: Spring JDBC Template
    }

    @Override
    public void removeAuthor(int id) {
        throw new RuntimeException("Not implemented"); //TODO: Spring JDBC Template
    }
}
