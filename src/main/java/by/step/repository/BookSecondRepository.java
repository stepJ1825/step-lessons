package by.step.repository;

import by.step.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookSecondRepository extends JpaRepository<Book, Integer> {

    // --------- READ (с пагинацией и сортировкой)

    Page<Book> findAll(Pageable pageable);

    List<Book> findAll(Sort sort);

    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Book> findByReleaseYearBetween(int from, int to, Pageable pageable);

}
