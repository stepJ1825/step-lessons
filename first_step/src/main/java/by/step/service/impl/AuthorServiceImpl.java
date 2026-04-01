package by.step.service.impl;

import by.step.entity.Author;
import by.step.repository.AuthorRepository;
import by.step.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> getAuthors() {
        return getAuthorList();
    }

    private List<Author> getAuthorList() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
                   isolation = Isolation.DEFAULT,
                   rollbackFor = RuntimeException.class, // NotEnoughMoneyException.class
                   rollbackForClassName = "RuntimeException.class",
                   noRollbackFor = IOException.class,    // TooMuchMoneyException.class
                   readOnly = true
    )
    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void validateAuthor(Author author) {
        if (author.getId() == null) {
            throw new RuntimeException();
        }
    }

}
