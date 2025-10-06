package by.step;

import by.step.controller.MainController;

/**
 * Погода. В БД хранится информация о погоде в различных регионах
 */
public class Main {
    public static void main(String[] args) {
        new MainController().start();
    }
}
