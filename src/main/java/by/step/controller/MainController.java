package by.step.controller;

import java.util.Scanner;

public class MainController {
    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("print 'w'");
            Scanner scanner = new Scanner(System.in);
            String next = scanner.nextLine().trim();

            switch (next) {
                case "weather", "w" -> {
                    new WeatherController().start();
                    running = false;
                }
                case "0", "exit" -> running = false;
            }
        }
    }
}