package by.step.repository.impl;

import by.step.model.simple.Author;
import by.step.model.simple.Book;
import by.step.model.simple.Genre;
import by.step.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.step.repository.impl.BookSpringJdbcRepositoryImpl.SELECT_ALL_BOOKS;

@Repository
@Profile("native") // Активируется только при профиле 'native'
@RequiredArgsConstructor
public class BookNativeJdbcRepositoryImpl implements BookRepository {

    private final DataSource dataSource;

    /// Маппинг Book + Author + Genre из одного ResultSet (при JOIN)
    private Book mapRowWithRelations(ResultSet rs) throws SQLException {
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
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_BOOKS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                books.add(mapRowWithRelations(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching books", e);
        }
        return books;
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

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowWithRelations(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding book by id", e);
        }
        return null;
    }

    @Override
    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, author_id, genre_id, year, rating) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, book.getTitle());
            stmt.setInt(2, book.getAuthor().getId());
            stmt.setInt(3, book.getGenre().getId());
            stmt.setInt(4, book.getYear());
            stmt.setFloat(5, book.getRating());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    book.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error adding book", e);
        }
    }

    @Override
    public void removeBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error removing book", e);
        }
    }

    @Override
    public List<Book> findBooksByAuthor(String authorSurname) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.id as book_id, b.title, b.year, b.rating, " +
                "a.id as author_id, a.first_name, a.surname, " +
                "g.id as genre_id, g.name as genre_name " +
                "FROM books b " +
                "JOIN authors a ON b.author_id = a.id " +
                "JOIN genres g ON b.genre_id = g.id " +
                "WHERE a.surname = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, authorSurname);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    books.add(mapRowWithRelations(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding books by author", e);
        }
        return books;
    }

    @Override
    public List<Book> findBooksByYearRange(int start, int end) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.id as book_id, b.title, b.year, b.rating, " +
                "a.id as author_id, a.first_name, a.surname, " +
                "g.id as genre_id, g.name as genre_name " +
                "FROM books b " +
                "JOIN authors a ON b.author_id = a.id " +
                "JOIN genres g ON b.genre_id = g.id " +
                "WHERE b.year BETWEEN ? AND ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, start);
            stmt.setInt(2, end);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    books.add(mapRowWithRelations(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding books by year range", e);
        }
        return books;
    }

    /**
     * Example from https://jenkov.com/tutorials/jdbc/batchupdate.html
     */
    @Override
    public void updateAllBooks(List<Book> books) {
        String sql = "UPDATE books set title = ? where id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            books.forEach(book -> {
                        try {
                            stmt.setString(1, book.getTitle());
                            stmt.setInt(2, book.getId());
                            stmt.addBatch();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );

            int[] affectedRecords = stmt.executeBatch();
//            System.out.println(affectedRecords);

        } catch (SQLException e) {
            throw new RuntimeException("Error adding book", e);
        }
    }

    @Override
    public void updateAllBooksWithNamedParams(List<Book> books) {
        throw new RuntimeException("Not applicable");
    }
}