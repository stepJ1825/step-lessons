package by.step;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;

public class LiquibaseDemo {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5430/postgres";
        String username = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Database database = DatabaseFactory.getInstance()
                                               .findCorrectDatabaseImplementation(new JdbcConnection(connection));

            updateXML(database);

//            updateYAML(database);

            System.out.println("Миграции Liquibase успешно применены!");
        } catch (LiquibaseException | java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateXML(Database database) throws LiquibaseException {
        Liquibase liquibaseXML = new Liquibase(
                "db/migration/liquibase/db.changelog-master.xml",       // путь к мастер-changelog
                new ClassLoaderResourceAccessor(),    // ищет в classpath
                database
        );
        liquibaseXML.update(); // применяет все не применённые изменения
    }

    private static void updateYAML(Database database) throws LiquibaseException {
        Liquibase liquibaseYAML = new Liquibase(
                "db/migration/liquibase-yaml-sql/changelog.yaml",       // путь к мастер-changelog
                new ClassLoaderResourceAccessor(),    // ищет в classpath
                database
        );
        liquibaseYAML.update();
    }
}