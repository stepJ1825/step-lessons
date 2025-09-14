package by.step.scratch;

import java.util.List;

public class LibraryDemo {
    public static void main(String[] args) {
        // Использование известных книг
        List<Book> famousBooks = TestDataGenerator.generateSampleBooks();
        System.out.println("=== Известные книги ===");
        famousBooks.forEach(System.out::println);

        // Использование случайных книг
        List<Book> randomBooks = TestDataGenerator.generateRandomBooks(20);
        System.out.println("\n=== Случайные книги ===");
        randomBooks.forEach(System.out::println);

        // Создание библиотеки
        Library library = new Library();
        famousBooks.forEach(library::addBook);
        randomBooks.forEach(library::addBook);

        System.out.println("\n=== Всего книг в библиотеке: " + library.getAllBooks().size() + " ===");
    }
}

class Library {
    // Реализация методов библиотеки
    public void addBook(Book book) { /* ... */ }
    public List<Book> getAllBooks() { /* ... */ return List.of(); }
}
