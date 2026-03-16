package by.step.repository;

import by.step.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    //-----------------------------------------
    // SPRING DATA JPA METHODS
    Optional<Author> findBySurname(String surname);

    List<Author> findByFirstNameStartingWith(String prefix);

    //-----------------------------------------
    // HQL QUERIES
    @Query("SELECT a FROM Author a WHERE a.firstName = :firstName AND a.surname = :surname")
    Optional<Author> findFullByName(
            @Param("firstName") String firstName,
            @Param("surname") String surname
    );

    @Query("SELECT DISTINCT b.author FROM Book b WHERE b.releaseYear >= :releaseYear")
    List<Author> findAuthorsWithBooksAfterYear(@Param("releaseYear") int releaseYear);

    //-----------------------------------------
    // NATIVE SQL QUERIES
    @Query(value = "SELECT a.* FROM authors a WHERE a.surname ILIKE %:suffix%",
           nativeQuery = true)
    List<Author> findBySurnameEndingWithNative(@Param("suffix") String suffix);

    //-----------------------------------------
    // NAMED QUERIES
    @Query(name = "Author.findBySurname")
    List<Author> findAuthorsBySurname(@Param("surname") String surname);

}
