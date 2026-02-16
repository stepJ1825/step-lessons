package by.step.repository.impl;

import by.step.model.Author;
import by.step.model.Book;
import by.step.model.Genre;
import by.step.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {

    private final DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Author> authorRowMapper = (ResultSet rs, int rowNum) -> new Author(
            rs.getInt("id"),
            rs.getString("first_name"),
            rs.getString("surname")
    );

    /// Маппинг Book + Author + Genre из одного ResultSet (при JOIN)
    private Author mapRowWithRelations(ResultSet rs) throws SQLException {
        return new Author(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("surname")
        );
    }

    @Override
    public List<Author> getAuthors() {
        List<Author> authors = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * from authors");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                authors.add(mapRowWithRelations(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching authors", e);
        }
        return authors;
    }

    @Override
    public void saveAuthor(Author author) {
        String sqlInsert = "INSERT INTO authors (first_name, surname) VALUES (?, ?)";
        String sqlUdate = """
                UPDATE authors SET
                first_name = ?,
                surname = ?
                where id = ?;
                """;
        String sql = author.getId() == null ? sqlInsert : sqlUdate;


        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, author.getFirstName());
            stmt.setString(2, author.getSurname());
            if (author.getId() != null) {
                stmt.setInt(3, author.getId());
            }

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    author.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error adding/updating author", e);
        }
    }

    @Override
    public Author getById(int id) {
        String sql = "SELECT * FROM authors WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, authorRowMapper, id);
    }

    @Override
    public void removeAuthor(int id) {
        String sql = "DELETE FROM authors WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
