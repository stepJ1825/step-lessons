package by.step;

import by.step.controller.MainController;
import by.step.repository.SomeRepository;
import by.step.repository.impl.BookRepositoryJSON;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        MainController mainController = context.getBean(MainController.class);
//        BookRepositoryJSON bean = context.getBean(BookRepositoryJSON.class);
//        System.out.println(bean);
        SomeRepository bean1 = context.getBean(SomeRepository.class);
        System.out.println(bean1);
        mainController.start();
    }
}
