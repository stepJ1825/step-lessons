package by.step.controller;

import java.util.Scanner;

public class MainController {
    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("print book or author");
            Scanner scanner = new Scanner(System.in);
            String next = scanner.nextLine().trim();

            switch (next) {
                case "book", "b" -> new BookController().start();
                case "author", "a" -> new AuthorController().start();
                case "0", "exit" -> running = false;
            }
        }
    }
}