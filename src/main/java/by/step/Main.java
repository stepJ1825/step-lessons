package by.step;

import by.step.controller.MainController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        MainController mainController = context.getBean(MainController.class);
//        Object mainController1 = context.getBean("mainController");
//        MainController mainController2 = context.getBean("mainController", MainController.class);
//
//        System.out.println(mainController == mainController1);
//        System.out.println(mainController2 == mainController1);

        mainController.start();


    }
}
