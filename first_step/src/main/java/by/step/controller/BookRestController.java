package by.step.controller;

import by.step.entity.Book;
import by.step.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        return ResponseEntity.ok(savedBook);
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

    @GetMapping("/author/{authorSurname}")
    public List<Book> findBooksByAuthor(
            @PathVariable String authorSurname) {
        return bookService.findBooksByAuthor(authorSurname);
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
    public ResponseEntity<Book> findById(@PathVariable("id") int id) {
        if (id > 1000) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping("/param-id")
    public Book findByIdInParam(
            @RequestParam(name = "id",
                    required = false) Integer id) {
        return id != null
                ? bookService.findById(id)
                : null;
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