package by.step.service.impl;

import by.step.model.Author;
import by.step.repository.AuthorRepository;
import by.step.repository.impl.AuthorRepositoryImpl;
import by.step.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl() {
        this.authorRepository = new AuthorRepositoryImpl();
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
