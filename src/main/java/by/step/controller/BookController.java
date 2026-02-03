package by.step.controller;

import by.step.model.Book;
import by.step.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public String addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return "Книга успешно добавлена!";
    }

    @DeleteMapping("/{id}")
    public String removeBook(@PathVariable int id) {
        bookService.removeBook(id);
        return "Книга удалена (если существовала)";
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/author/{authorName}")
    public List<Book> findBooksByAuthor(@PathVariable String authorName) {
        return bookService.findBooksByAuthor(authorName);
    }

    @GetMapping("/average-rating")
    public float getAverageRating() {
        return bookService.getAverageRating();
    }

    @GetMapping("/grouped-by-genre")
    public Map<String, List<Book>> getBooksGroupedByGenre() {
        return bookService.getBooksGroupedByGenre();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable int id) {
        return bookService.findById(id);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String keyword) {
        return bookService.searchBooks(keyword);
    }

    @GetMapping("/titles")
    public String getBookTitlesAsString() {
        return bookService.getBookTitlesAsString();
    }

    @GetMapping("/author-statistics")
    public Map<String, Serializable> getAuthorStatistics(@RequestParam String authorName) {
        return bookService.getAuthorStatistics(authorName);
    }
}