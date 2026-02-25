package by.step.repository;

import by.step.model.jpa.AuthorJPA;
import by.step.model.jpa.BookJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookJPARepository extends JpaRepository<BookJPA, Integer> {

    List<BookJPA> findByAuthor(AuthorJPA author);

    List<BookJPA> findByYearBetween(int start, int end);

    List<BookJPA> findByAuthorNotNull();

    List<BookJPA> findByAuthorIsNull();

    List<BookJPA> findByAuthorFirstNameContains(String firstName);

    @Query(nativeQuery = true, value = "SELECT * FROM books;")
    List<BookJPA> findAllNative();
}
