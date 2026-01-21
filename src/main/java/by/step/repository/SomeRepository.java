package by.step.repository;

import by.step.repository.db.ConnectionPool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SomeRepository {

    private final ConnectionPool connectionPool;

    public List<Object> getAllEntities(){
        Connection connection = connectionPool.getConnection();
        return Collections.emptyList();
    }
}
