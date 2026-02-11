package by.step.config;

import by.step.repository.genre.GenreRepository;
import by.step.repository.genre.GenreRepositoryImpl2;
import by.step.repository.genre.GenreService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;

import java.text.SimpleDateFormat;

@Configuration(enforceUniqueMethods = false, proxyBeanMethods = true)
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
