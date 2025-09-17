package by.step.controller;

import by.step.service.BookService;
import by.step.service.BookServiceImpl;

public class MainController {
    private final BookService bookService;

    public MainController() {
        this.bookService = new BookServiceImpl();
    }

//    public void addBook(Book book){
//        System.out.println("Добавляем книгу");
//        bookService.addBook(book);
//        System.out.println("Книга добавлена");
//    }
//
//    public void removeBook(int id){
//
//    }
//
//    List<Book> findBooksByAuthor(String author);
//
//    List<Book> findBooksByYearRange(int start, int end);
//
//    List<Book> getAllBooks();
//
//    List<Book> getBooksByFilter(BookFilter filter);
//
//    float getAverageRating();
//
//    Map<String, List<Book>> getBooksGroupedByGenre();
//
//    Book findById(int id);
//
//    List<Book> searchBooks(String keyword); //поиск по названию и автору (игнорируя регистр)
//
//    String getBookTitlesAsString(); // все названия книг в виде строки через запятую
//
//    Map<String, Serializable> getAuthorStatistics(String author);
}
