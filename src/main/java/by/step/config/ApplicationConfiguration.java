package by.step.config;

import by.step.repository.AuthorRepository;
import by.step.repository.BookRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "by.step",
               useDefaultFilters = false,
               includeFilters = {
                       @Filter(type = FilterType.ANNOTATION, value = Component.class),
                       @Filter(type = FilterType.ASSIGNABLE_TYPE, value = AuthorRepository.class),
                       @Filter(type = FilterType.ASSIGNABLE_TYPE, value = BookRepository.class),
                       @Filter(type = FilterType.REGEX, pattern = "com\\..+Repository")
               })
public class ApplicationConfiguration {
}
