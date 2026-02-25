package by.step.repository;

import by.step.model.jpa.AuthorJPA;
import by.step.model.jpa.BookJPA;
import by.step.model.simple.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookJPARepository extends JpaRepository<BookJPA, Integer> {

    List<BookJPA> findBooksByAuthor(AuthorJPA author);

    List<BookJPA> findBooksByYearBetween(int start, int end);
}
