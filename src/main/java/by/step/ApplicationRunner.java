package by.step;

import by.step.entity.Book;
import by.step.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class ApplicationRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ApplicationRunner.class, args);
        BookRepository bookRepository = context.getBean(BookRepository.class);
        List<Book> allBooks = bookRepository.findAll();
//        BookJPA referenceById = bookRepository.findById(3).get();
//        bookRepository.findBooksByYearBetween(2017,2018);
//        System.out.println(referenceById);
//        bookRepository.updateAllBooksWithNamedParams(allBooks);
//        bookRepository.removeBook(1);
//        ((BookSpringJdbcRepositoryImpl)bookRepository).updateAllBooks();
//        ((BookSpringJdbcRepositoryImpl)bookRepository).updateAllBooksNamed();

//        AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
//        List<Author> authors = authorRepository.getAuthors();
//        Author byId = authorRepository.getById(101);
//        byId.setSurname(LocalTime.now().toString());
//        authorRepository.saveAuthor(byId);
//        Author builded = Author.builder().surname("testovik").firstName("test").build();
//        authorRepository.saveAuthor(builded);
//        authorRepository.removeAuthor(builded.getId());
    }
}
