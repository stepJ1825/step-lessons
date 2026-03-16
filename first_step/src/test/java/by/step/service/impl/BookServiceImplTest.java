package by.step.service.impl;

import by.step.entity.Book;
import by.step.repository.BookRepository;
import by.step.service.BookService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Profile("test")
//@Sql(scripts = {
//        "/sql/V3.0.1__Create_tables.sql",
//        "/sql/V3.0.2__Insert_authors.sql",
//        "/sql/V3.0.3__Insert_genres.sql",
//        "/sql/V3.0.4__Insert_books.sql"
//}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
//@Sql(scripts = {
//        "classpath:sql/cleanup.sql"
//}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class BookServiceImplTest {
    //TODO: поправить тест
    @Autowired
    private BookRepository repository;
    private BookService service = new BookServiceImpl(repository);

    @Test
    void getAuthorStatistics() {
        Map<String, Serializable> brown = service.getAuthorStatistics("Brown");
        Assertions.assertThat(brown).isNotNull();
    }

    @Test
    void testPrivateMethod(){
//        Method getAverageRating = Arrays.stream(service.getClass().getDeclaredMethods())
//                .filter(method -> method.getName().equals("getAverageRating"))
//                .findFirst()
//                .get();
//        getAverageRating.invoke()
    }
}