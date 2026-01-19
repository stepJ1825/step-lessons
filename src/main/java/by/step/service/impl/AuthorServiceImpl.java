package by.step.service.impl;

import by.step.model.Author;
import by.step.repository.AuthorRepository;
import by.step.repository.impl.AuthorRepositoryImpl;
import by.step.service.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.getAuthors();
    }

    @Override
    public void addAuthor(Author author) {
        authorRepository.addAuthor(author);
    }
}
