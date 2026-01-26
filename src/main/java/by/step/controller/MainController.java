package by.step.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class MainController {

    private final BookController bookController;
    @Getter
    @Setter
    @Autowired
    private AuthorController authorController;

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