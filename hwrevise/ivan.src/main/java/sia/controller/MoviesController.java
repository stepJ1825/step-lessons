package sia.controller;

import sia.model.Movies;
import sia.service.MoviesService;
import sia.service.impl.MoviesServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MoviesController {
    private final MoviesService service;
    private final Scanner scanner;

    public MoviesController() {
        this.service = new MoviesServiceImpl();
        this.scanner = new Scanner(System.in);
    }

    public void start() throws IOException {
        boolean running = true;

        while (running) {
            printMenu();
            System.out.println("введите число для выбора опции: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> service.getMoviesFromLastTwoYears().forEach(System.out::println);
                case 2 -> service.getActorsFromMovieByTitle("Oppenheimer").forEach(System.out::println);
                case 3 -> service.getActorsThatGetActionAtLeastNMovies(2).forEach(System.out::println);
                case 4 -> service.getActorsWhoAreTheDirectors().forEach(System.out::println);
                case 5 -> {
                    List<Movies> deletedMovies = service.deleteMoviesThatElderThanNYears(25);
                    System.out.println(deletedMovies);
                }
                case 0 -> {
                    System.out.println("выход из программы...");
                    running = false;
                }
                default -> System.out.println("неверный выбор. попробуйте снова.");
            }

            if (running) {
                System.out.println("\nнажмите enter для продолжения...");
                scanner.nextLine();
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=========МЕНЮ=========");
        System.out.println("1. фильмы, которые вышли на экран за последние два года");
        System.out.println("2. актеры фильма 'Oppenheimer'");
        System.out.println("3. актеры, которые принимали участие минимум в двух фильмах");
        System.out.println("4. актеры, которые были режиссерами фильмов");
        System.out.println("5. удаление фильмов, которые вышли больше 25 лет назад");
        System.out.println("0. выход");
        System.out.println("=======================");
    }
}
