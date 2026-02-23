package by.step.service.impl;

import by.step.model.simple.Author;
import by.step.repository.AuthorRepository;
import by.step.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> getAuthors() {
        return authorRepository.getAuthors();
    }

    @Override
    public void addAuthor(Author author) {
        authorRepository.saveAuthor(author);
    }
}
