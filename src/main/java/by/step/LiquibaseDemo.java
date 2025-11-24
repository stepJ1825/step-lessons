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
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Database database = DatabaseFactory.getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(connection));

//            String xmlChangelog = "db/migration/liquibase/db.changelog-master.xml";     // путь к мастер-changelog
//            updateDatabase(database, xmlChangelog);
            String yamlChangelog = "db/migration/liquibase-yaml-sql/changelog.yaml";
            updateDatabase(database, yamlChangelog);

            System.out.println("Миграции Liquibase успешно применены!");
        } catch (LiquibaseException | java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateDatabase(Database database, String changelogPath) throws LiquibaseException {
        Liquibase liquibaseXML = new Liquibase(
                changelogPath,       // путь к мастер-changelog
                new ClassLoaderResourceAccessor(),    // ищет в classpath
                database
        );
        liquibaseXML.update(); // применяет все не применённые изменения
    }
}