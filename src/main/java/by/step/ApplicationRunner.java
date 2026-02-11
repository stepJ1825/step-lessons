package by.step;

import by.step.config.DatabaseConfiguration;
import by.step.repository.BookRepository;
import by.step.repository.impl.AuthorRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ApplicationRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ApplicationRunner.class, args);
        BookRepository bean = run.getBean(BookRepository.class);
        System.out.println(bean);
    }
}
