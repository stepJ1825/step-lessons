package by.step;

import by.step.config.ApplicationConfiguration;
import by.step.controller.MainController;
import by.step.repository.AuthorRepository;
import by.step.repository.SomeRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainWithParentContext {
    public static void main(String[] args) {
        // 1. Создаём XML-контекст
        ClassPathXmlApplicationContext parentContext =
                new ClassPathXmlApplicationContext("application.xml");

        // 2. Создаём Java-контекст и устанавливаем родителя
        AnnotationConfigApplicationContext childContext =
                new AnnotationConfigApplicationContext();
        childContext.setParent(parentContext); // ← иерархия!
        childContext.register(ApplicationConfiguration.class);
        childContext.refresh();


        MainController mainController = parentContext.getBean(MainController.class);
        Object bookRepository = parentContext.getBean("bookRepository");
        System.out.println(bookRepository);
        SomeRepository someRepository = parentContext.getBean(SomeRepository.class);
        System.out.println(someRepository);
        AuthorRepository authorRepository = parentContext.getBean(AuthorRepository.class);
        System.out.println(authorRepository);
        mainController.start();
    }
}
