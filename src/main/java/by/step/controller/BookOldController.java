//package by.step.controller;
//
//import by.step.model.Author;
//import by.step.model.Book;
//import by.step.model.Genre;
//import by.step.service.BookFilter;
//import by.step.service.BookService;
//import by.step.util.BookFilterUtil;
//import jakarta.annotation.PostConstruct;
//import org.springframework.stereotype.Component;
//
//import java.io.Serializable;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
//import java.util.stream.IntStream;
//
//@Component
//public class BookOldController {
//    private final BookService bookService;
//    private Scanner scanner;
//
//    public BookOldController(BookService bookService) {
//        this.bookService = bookService;
//    }
//
//    // Метод инициализации — вызывается Spring после создания бина
//    @PostConstruct
//    public void init() {
//        this.scanner = new Scanner(System.in);
//    }
//
//    public void start() {
//        boolean running = true;
//
//        while (running) {
//            printMenu();
//            int choice = getIntInput("Выберите опцию: ");
//
//            switch (choice) {
//                case 1 -> addBook();
//                case 2 -> removeBook();
//                case 3 -> findBooksByAuthor();
//                case 4 -> findBooksByYearRange();
//                case 5 -> showAllBooks();
//                case 6 -> filterBooks();
//                case 7 -> showAverageRating();
//                case 8 -> showBooksGroupedByGenre();
//                case 9 -> findBookById();
//                case 10 -> searchBooks();
//                case 11 -> showBookTitles();
//                case 12 -> showAuthorStatistics();
//                case 0 -> {
//                    running = false;
//                    System.out.println("Выход из программы...");
//                }
//                default -> System.out.println("Неверный выбор. Попробуйте снова.");
//            }
//
//            if (running) {
//                System.out.println("\nНажмите Enter для продолжения...");
//                scanner.nextLine();
//            }
//        }
//    }
//
//    private void printMenu() {
//        System.out.println("\n=== МЕНЮ БИБЛИОТЕКИ ===");
//        System.out.println("1. Добавить книгу");
//        System.out.println("2. Удалить книгу");
//        System.out.println("3. Найти книги по автору");
//        System.out.println("4. Найти книги по диапазону годов");
//        System.out.println("5. Показать все книги");
//        System.out.println("6. Фильтровать книги");
//        System.out.println("7. Показать средний рейтинг");
//        System.out.println("8. Показать книги по жанрам");
//        System.out.println("9. Найти книгу по ID");
//        System.out.println("10. Поиск книг по ключевому слову");
//        System.out.println("11. Показать все названия книг");
//        System.out.println("12. Статистика по автору");
//        System.out.println("0. Выход");
//        System.out.println("=======================");
//    }
//
//    private void addBook() {
//        System.out.println("\n=== ДОБАВЛЕНИЕ КНИГИ ===");
//
//        int id = getIntInput("ID книги: ");
//        String title = getStringInput("Название: ");
//
//        System.out.println("Автор:");
//        int authorId = getIntInput("ID автора: ");
//        String firstName = getStringInput("Имя автора: ");
//        String surname = getStringInput("Фамилия автора: ");
//        Author author = new Author(authorId, firstName, surname);
//
//        System.out.println("Жанр:");
//        int genreId = getIntInput("ID жанра: ");
//        String genreName = getStringInput("Название жанра: ");
//        Genre genre = new Genre(genreId, genreName);
//
//        int year = getIntInput("Год издания: ");
//        float rating = getFloatInput("Рейтинг (0.0-5.0): ");
//
//        Book book = new Book(id, title, author, genre, year, rating);
//        bookService.addBook(book);
//        System.out.println("Книга успешно добавлена!");
//    }
//
//    private void removeBook() {
//        System.out.println("\n=== УДАЛЕНИЕ КНИГИ ===");
//        int id = getIntInput("Введите ID книги для удаления: ");
//        bookService.removeBook(id);
//        System.out.println("Книга удалена (если существовала)");
//    }
//
//    private void findBooksByAuthor() {
//        System.out.println("\n=== ПОИСК ПО АВТОРУ ===");
//        String author = getStringInput("Введите имя автора: ");
//        List<Book> books = bookService.findBooksByAuthor(author);
//        displayBooks(books);
//    }
//
//    private void findBooksByYearRange() {
//        System.out.println("\n=== ПОИСК ПО ДИАПАЗОНУ ЛЕТ ===");
//        int start = getIntInput("Начальный год: ");
//        int end = getIntInput("Конечный год: ");
//        List<Book> books = bookService.findBooksByYearRange(start, end);
//        displayBooks(books);
//    }
//
//    private void showAllBooks() {
//        System.out.println("\n=== ВСЕ КНИГИ ===");
//        List<Book> books = bookService.getAllBooks();
//        displayBooks(books);
//    }
//
//    private void filterBooks() {
//        System.out.println("\n=== ФИЛЬТРАЦИЯ КНИГ ===");
//        System.out.println("1. По жанру");
//        System.out.println("2. По минимальному рейтингу");
//        System.out.println("3. По максимальному рейтингу");
//        System.out.println("4. Классические книги (до 1950, рейтинг >=4)");
//        System.out.println("5. Бестселлеры (рейтинг 5)");
//
//        int choice = getIntInput("Выберите тип фильтра: ");
//
//        BookFilter filter = null;
//
//        switch (choice) {
//            case 1 -> {
//                String genre = getStringInput("Введите жанр: ");
//                filter = BookFilterUtil.byGenre(genre);
//            }
//            case 2 -> {
//                float minRating = getFloatInput("Минимальный рейтинг: ");
//                filter = BookFilterUtil.byMinRating((int) minRating);
//            }
//            case 3 -> {
//                float maxRating = getFloatInput("Максимальный рейтинг: ");
//                filter = BookFilterUtil.byMaxRating((int) maxRating);
//            }
//            case 4 -> filter = BookFilterUtil.isClassic();
//            case 5 -> filter = BookFilterUtil.isBestseller();
//            default -> {
//                System.out.println("Неверный выбор");
//                return;
//            }
//        }
//
//        List<Book> books = bookService.getBooksByFilter(filter);
//        displayBooks(books);
//    }
//
//    private void showAverageRating() {
//        System.out.println("\n=== СРЕДНИЙ РЕЙТИНГ ===");
//        float average = bookService.getAverageRating();
//        System.out.printf("Средний рейтинг всех книг: %.2f\n", average);
//    }
//
//    private void showBooksGroupedByGenre() {
//        System.out.println("\n=== КНИГИ ПО ЖАНРАМ ===");
//        Map<String, List<Book>> booksByGenre = bookService.getBooksGroupedByGenre();
//
//        for (Map.Entry<String, List<Book>> entry : booksByGenre.entrySet()) {
//            System.out.println("\n--- " + entry.getKey() + " ---");
//            displayBooks(entry.getValue());
//        }
//    }
//
//    private void findBookById() {
//        System.out.println("\n=== ПОИСК ПО ID ===");
//        int id = getIntInput("Введите ID книги: ");
//        Book book = bookService.findById(id);
//
//        if (book != null) {
//            System.out.println("Найдена книга:");
//            System.out.println(book);
//        } else {
//            System.out.println("Книга с ID " + id + " не найдена");
//        }
//    }
//
//    private void searchBooks() {
//        System.out.println("\n=== ПОИСК ПО КЛЮЧЕВОМУ СЛОВУ ===");
//        String keyword = getStringInput("Введите ключевое слово: ");
//        List<Book> books = bookService.searchBooks(keyword);
//        displayBooks(books);
//    }
//
//    private void showBookTitles() {
//        System.out.println("\n=== ВСЕ НАЗВАНИЯ КНИГ ===");
//        String titles = bookService.getBookTitlesAsString();
//        System.out.println(titles);
//    }
//
//    private void showAuthorStatistics() {
//        System.out.println("\n=== СТАТИСТИКА ПО АВТОРУ ===");
//        String author = getStringInput("Введите имя автора: ");
//        Map<String, Serializable> stats = bookService.getAuthorStatistics(author);
//
//        for (Map.Entry<String, Serializable> entry : stats.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
//    }
//
//    private void displayBooks(List<Book> books) {
//        if (books.isEmpty()) {
//            System.out.println("Книги не найдены");
//            return;
//        }
//
//        System.out.println("Найдено книг: " + books.size());
//        IntStream.range(0, books.size())
//                 .forEach(i -> System.out.printf("%d. %s\n", i + 1, books.get(i)));
//    }
//
//    private String getStringInput(String prompt) {
//        System.out.print(prompt);
//        return scanner.nextLine().trim();
//    }
//
//    private int getIntInput(String prompt) {
//        while (true) {
//            try {
//                System.out.print(prompt);
//                return Integer.parseInt(scanner.nextLine().trim());
//            } catch (NumberFormatException e) {
//                System.out.println("Пожалуйста, введите целое число");
//            }
//        }
//    }
//
//    private float getFloatInput(String prompt) {
//        while (true) {
//            try {
//                System.out.print(prompt);
//                return Float.parseFloat(scanner.nextLine().trim());
//            } catch (NumberFormatException e) {
//                System.out.println("Пожалуйста, введите число");
//            }
//        }
//    }
//}