package by.step.controller;

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

public class MainController {

    private final BookController bookController;
    @Getter
    @Setter
    private AuthorController authorController;

    public MainController(BookController bookController) {
        this.bookController = bookController;
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("print book or author");
            Scanner scanner = new Scanner(System.in);
            String next = scanner.nextLine().trim();

            switch (next) {
                case "book", "b" -> bookController.start();
                case "author", "a" -> authorController.start();
                case "0", "exit" -> running = false;
            }
        }
    }
}