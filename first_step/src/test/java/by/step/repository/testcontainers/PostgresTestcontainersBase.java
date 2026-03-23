package by.step.repository.testcontainers;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;

/**
 * Shared Postgres container for repository integration tests. Flyway migrations are executed by Spring Boot on
 * startup.
 */
@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class PostgresTestcontainersBase {

    @Container
    static final PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:17")
                    .withDatabaseName("testdb")
                    .withUsername("postgres")
                    .withPassword("postgres")
                    .withStartupTimeout(Duration.ofMinutes(3));

    @DynamicPropertySource
    static void registerDataSource(DynamicPropertyRegistry registry) {
        // Ensure container is started before Spring creates the DataSource/Hikari pool.
        // Without this, Spring may try to connect and hit Hikari timeout.
        postgres.start();

        // Use the container database instead of values from application-test.yaml
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.driver-class-name", () -> "org.postgresql.Driver");

        // Enable Flyway for schema + seed data from src/main/resources/db/migration/flyway
        registry.add("spring.flyway.enabled", () -> "true");
        registry.add("spring.flyway.locations", () -> "classpath:db/migration/flyway");
        registry.add("spring.flyway.baseline-on-migrate", () -> "true");
        registry.add("spring.flyway.validate-on-migrate", () -> "false");

        // Avoid Spring trying to manage schema itself (Flyway already creates it)
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "validate");

        // Make sure Spring SQL initializer does not try to run H2 scripts
        registry.add("spring.sql.init.mode", () -> "never");

        // Hikari: allow more time for container startup (especially on first pull)
        registry.add("spring.datasource.hikari.connection-timeout", () -> "120000");
        registry.add("spring.datasource.hikari.initialization-fail-timeout", () -> "120000");
    }

    @Autowired
    private javax.sql.DataSource dataSource;

    /**
     * Reset DB state for each test class (clean + migrate), so that modifications in a previous test class won't affect
     * next ones.
     */
    @BeforeAll
    void resetDatabase() {
        Flyway flyway = Flyway.configure()
                              .dataSource(dataSource)
                              .locations("classpath:db/migration/flyway")
                              .baselineOnMigrate(true)
                              .validateOnMigrate(false)
                              .cleanDisabled(false)
                              .load();

        flyway.clean();
        flyway.migrate();
    }
}

