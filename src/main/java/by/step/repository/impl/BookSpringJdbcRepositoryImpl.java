package by.step.repository.impl;

import by.step.model.simple.Author;
import by.step.model.simple.Book;
import by.step.model.simple.Genre;
import by.step.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

@Repository
@Profile("spring-jdbc | test") // Активируется только при профиле 'spring-jdbc'
@RequiredArgsConstructor
public class BookSpringJdbcRepositoryImpl implements BookRepository {

    static final String SELECT_ALL_BOOKS = "SELECT b.id as book_id, b.title, b.year, b.rating, " +
                                           "a.id as author_id, a.first_name, a.surname, " +
                                           "g.id as genre_id, g.name as genre_name " +
                                           "FROM books b " +
                                           "JOIN authors a ON b.author_id = a.id " +
                                           "JOIN genres g ON b.genre_id = g.id";

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RowMapper<Book> bookRowMapper = (ResultSet rs, int rowNum) -> {
        Author author = new Author(
                rs.getInt("author_id"),
                rs.getString("first_name"),
                rs.getString("surname")
        );

        Genre genre = new Genre(
                rs.getInt("genre_id"),
                rs.getString("genre_name")
        );

        return new Book(
                rs.getInt("book_id"),
                rs.getString("title"),
                author,
                genre,
                rs.getInt("year"),
                rs.getFloat("rating")
        );
    };

    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query(SELECT_ALL_BOOKS, bookRowMapper);
    }

    @Override
    public Book findById(int id) {
        String sql = "SELECT b.id as book_id, b.title, b.year, b.rating, " +
                     "a.id as author_id, a.first_name, a.surname, " +
                     "g.id as genre_id, g.name as genre_name " +
                     "FROM books b " +
                     "JOIN authors a ON b.author_id = a.id " +
                     "JOIN genres g ON b.genre_id = g.id " +
                     "WHERE b.id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, bookRowMapper, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, author_id, genre_id, year, rating) VALUES (?, ?, ?, ?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                    ps.setString(1, book.getTitle());
                    ps.setInt(2, book.getAuthor().getId());
                    ps.setInt(3, book.getGenre().getId());
                    ps.setInt(4, book.getYear());
                    ps.setFloat(5, book.getRating());
                    return ps;
                }, keyHolder
        );

        if (keyHolder.getKey() != null) {
            book.setId(keyHolder.getKey().intValue());
        }
    }

    @Override
    public void removeBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Book> findBooksByAuthor(String authorSurname) {
        String sql = "SELECT b.id as book_id, b.title, b.year, b.rating, " +
                     "a.id as author_id, a.first_name, a.surname, " +
                     "g.id as genre_id, g.name as genre_name " +
                     "FROM books b " +
                     "JOIN authors a ON b.author_id = a.id " +
                     "JOIN genres g ON b.genre_id = g.id " +
                     "WHERE a.surname = ?";
        return jdbcTemplate.query(sql, bookRowMapper, authorSurname);
    }

    @Override
    public List<Book> findBooksByYearRange(int start, int end) {
        String sql = "SELECT b.id as book_id, b.title, b.year, b.rating, " +
                     "a.id as author_id, a.first_name, a.surname, " +
                     "g.id as genre_id, g.name as genre_name " +
                     "FROM books b " +
                     "JOIN authors a ON b.author_id = a.id " +
                     "JOIN genres g ON b.genre_id = g.id " +
                     "WHERE b.year BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, bookRowMapper, start, end);
    }

    /**
     * Example from https://habr.com/ru/articles/703828/
     */
    @Override
    public void updateAllBooks(List<Book> books) {
        String sql = "UPDATE books set title = ? where id = ?";
        List<Object[]> args = books.stream()
                                      .map(book -> new Object[]{book.getTitle() + "1", book.getId()})
                                      .toList();
        jdbcTemplate.batchUpdate(sql, args);
    }

    @Override
    public void updateAllBooksWithNamedParams(List<Book> books) {
        String sql = "UPDATE books set title = :title where id = :book_id";
        var args = books.stream()
                           .map(book -> Map.of(
                                   "book_id", book.getId(),
                                   "title", book.getTitle() + "2"
                           ))
                           .map(MapSqlParameterSource::new)
                           .toArray(MapSqlParameterSource[]::new);
        namedParameterJdbcTemplate.batchUpdate(sql, args);
    }
}