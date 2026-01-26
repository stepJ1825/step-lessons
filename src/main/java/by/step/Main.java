package by.step;

import by.step.controller.MainController;
import by.step.repository.AuthorRepository;
import by.step.repository.SomeRepository;
import by.step.repository.impl.BookRepositoryJSON;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        MainController mainController = context.getBean(MainController.class);
        Object bookRepository = context.getBean("bookRepository");
        System.out.println(bookRepository);
        SomeRepository someRepository = context.getBean(SomeRepository.class);
        System.out.println(someRepository);
        AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
        System.out.println(authorRepository);
        mainController.start();
    }
}
