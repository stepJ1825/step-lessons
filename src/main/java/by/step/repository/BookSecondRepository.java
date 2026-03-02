package by.step.repository;

import by.step.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookSecondRepository extends JpaRepository<Book, Integer> {

    // --------- READ (с пагинацией и сортировкой)

    Page<Book> findAll(Pageable pageable);

    List<Book> findAll(Sort sort);

    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Book> findByYearBetween(int from, int to, Pageable pageable);

    // --------- CREATE / UPDATE

    @Override
    <S extends Book> S save(S entity);

    @Override
    <S extends Book> List<S> saveAll(Iterable<S> entities);

    // --------- READ (одна сущность)

    @Override
    Optional<Book> findById(Integer id);

    // --------- DELETE

    @Override
    void deleteById(Integer id);

    @Override
    void deleteAllById(Iterable<? extends Integer> ids);
}
