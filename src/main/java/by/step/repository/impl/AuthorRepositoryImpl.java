package by.step.repository.impl;

import by.step.model.Author;
import by.step.repository.AuthorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
    @Override
    public List<Author> getAuthors() {
        throw new RuntimeException("Not implemented"); //TODO
    }

    @Override
    public void addAuthor(Author author) {
        throw new RuntimeException("Not implemented"); //TODO
    }

    @Override
    public void saveAuthor(Author author) {
        throw new RuntimeException("Not implemented"); //TODO
    }

    @Override
    public void removeAuthor(int id) {
        throw new RuntimeException("Not implemented"); //TODO
    }
}
