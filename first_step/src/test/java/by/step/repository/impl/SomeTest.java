package by.step.repository.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.sql.SQLException;

//@SpringBootTest(classes = ApplicationRunner.class)
//@Sql({
//        "classpath:sql/V3.0.1__Create_tables.sql",
//        "classpath:sql/V3.0.2__Insert_authors.sql",
//        "classpath:sql/V3.0.3__Insert_genres.sql",
//        "classpath:sql/V3.0.4__Insert_books.sql"
//})
@ActiveProfiles("test")
@JdbcTest
public class SomeTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void checkDataSource() throws SQLException {
        try (var conn = dataSource.getConnection()) {
            System.out.println("DB URL: " + conn.getMetaData().getURL());
            // Должно вывести: jdbc:h2:mem:test_mem
            Assertions.assertTrue(conn.getMetaData().getURL().contains("h2"));
        }
    }

}
