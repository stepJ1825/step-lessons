package by.step.config;

import by.step.repository.AuthorRepository;
import by.step.repository.BookRepository;
import jakarta.servlet.ServletContext;
import lombok.SneakyThrows;
import org.springframework.context.annotation.*;
import by.step.repository.genre.GenreRepository;
import by.step.repository.genre.GenreRepositoryImpl2;
import by.step.repository.genre.GenreService;
import by.step.repository.impl.AuthorRepositoryImpl;
import by.step2.config.WebConfig;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;

@Configuration(enforceUniqueMethods = false, proxyBeanMethods = true)
//@PropertySource("classpath:application.properties")
//@PropertySource(
//        value = "classpath:application-${spring.profiles.active}.properties",
//        ignoreResourceNotFound = true
//)
//@Import(WebConfig.class)
//@ComponentScan(basePackages = "by.step",
//               useDefaultFilters = false,
//               includeFilters = {
//                       @Filter(type = FilterType.ANNOTATION, value = Component.class),
//                       @Filter(type = FilterType.ASSIGNABLE_TYPE, value = AuthorRepository.class),
//                       @Filter(type = FilterType.ASSIGNABLE_TYPE, value = BookRepository.class),
//                       @Filter(type = FilterType.REGEX, pattern = "by\\..+Repository")
//               })
public class ApplicationConfiguration {

    /*
    <!--    <bean id="dateFormatter" class="java.text.SimpleDateFormat">-->
    <!--        <constructor-arg value="${app.date.format}"/>-->
    <!--    </bean>-->
     */
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public SimpleDateFormat dateFormatter(
            @Value("${app.date.format}") String pattern) {
        return new SimpleDateFormat(pattern);
    }

    @Bean("genreRepository3")
    public GenreRepository genreRepository(){
        return new GenreRepositoryImpl2();
    }

    @Bean("genreService3")
    public GenreService genreService(){
        return new GenreService(genreRepository());
    }

    @Bean("genreService4")
    @Primary
    public GenreService genreService(@Qualifier("genreRepository3") GenreRepository genreRepository){
        return new GenreService(genreRepository);
    }

    @Bean
    @Profile("!prod | test | dev")   // ! & | - доступны логические операции, можно ставить над классов и над методом
    public GenreService genreService5(@Qualifier("genreRepository3") GenreRepository genreRepository){
        return new GenreService(null);
    }

    @SneakyThrows
    @Bean
    @Profile("prod")   // ! & | - доступны логические операции, можно ставить над классов и над методом
    public GenreService genreService6(@Qualifier("genreRepository3") GenreRepository genreRepository){
        Thread.sleep(10000L);
        return new GenreService(genreRepository);
    }

}
