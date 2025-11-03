package sia.controller;

import java.io.IOException;
import java.util.Scanner;

public class MainController {
    public void start() throws IOException {
        boolean running = true;

        while (running) {
            System.out.println("напишите 'фильм' или 'выход': ");
            Scanner scanner = new Scanner(System.in);
            String next = scanner.nextLine().trim();

            switch (next) {
                case "movie", "фильм" -> new MoviesController().start();
                case "exit", "выход" -> running = false;
            }
        }
    }
}
