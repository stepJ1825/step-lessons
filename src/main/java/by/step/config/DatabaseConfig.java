package by.step.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration

public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .baselineOnMigrate(true)
                // .baselineVersion("V1")
                // .dataSource("jdbc:postgresql://localhost:5430/postgres", "postgres", "postgres")
                .dataSource(dataSource())
                .locations("classpath:db/migration/flyway")
                .baselineOnMigrate(true)
                .validateOnMigrate(false)
                .load();
        // Запуск миграций
        flyway.migrate();
        return flyway;
    }

    @Bean
    @Profile("spring-jdbc | native") // Активируется только при профиле 'spring-jdbc' или 'native'
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    @Bean
    @Profile("spring-jdbc") // Активируется только при профиле 'spring-jdbc'
    public JdbcTemplate jdbcTemplate(DataSource dataSource, @Value("${spring.jdbc.fetchSize}") Integer fetchSize) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setFetchSize(fetchSize);
        return jdbcTemplate;
    }
}
