package by.step;

import by.step.controller.BookController;
import by.step.service.BookService;
import by.step.service.BookServiceImpl;

public class Main {
    public static void main(String[] args) {
        // Создаем зависимости
        BookService bookService = new BookServiceImpl();
        BookController controller = new BookController(bookService);

        // Запускаем приложение
        controller.start();
    }
}
