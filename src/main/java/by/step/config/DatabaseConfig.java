package by.step.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                              .baselineOnMigrate(true)
                              // .baselineVersion("V1")
                              .dataSource("jdbc:postgresql://localhost:5430/postgres", "postgres", "postgres")
                              .locations("classpath:db/migration/flyway")
                              .baselineOnMigrate(true)
                              .validateOnMigrate(false)
                              .load();
        // Запуск миграций
        flyway.migrate();
        return flyway;
    }

    @Bean
    @Profile("spring-jdbc | native") // Активируется только при профиле 'spring-jdbc'
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost:5430/postgres");
        ds.setUsername("postgres");
        ds.setPassword("postgres");
        return ds;
    }

    @Bean
    @Profile("spring-jdbc") // Активируется только при профиле 'spring-jdbc'
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
