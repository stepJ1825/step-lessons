package by.step;

import lombok.SneakyThrows;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;

public class FlywayDemo {
    public static void main(String[] args) {

        //Проверка соединения с БД (в случае ошибок)
        checkConnection();

        // Настройка Flyway
        Flyway flyway = Flyway.configure()
                              .dataSource("jdbc:postgresql://localhost:5430/postgres", "postgres", "postgres")
                              .locations("classpath:db/migration/flyway")
                              .load();
        // Запуск миграций
        flyway.migrate();

        System.out.println("Миграции успешно применены!");
    }

    @SneakyThrows
    public static void checkConnection() {
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5430/postgres",
                "postgres",
                "postgres"
        );
        System.out.println("Подключение успешно!");
        conn.close();
    }
}
