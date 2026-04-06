package by.step.controller.book;

import by.step.entity.Book;
import by.step.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @PostMapping
    public String addBook(@RequestBody Book book) {
        log.info("Received request to add new book: {}", book.getTitle());
        try {
            Book savedBook = bookService.addBook(book);
            log.info("Successfully added book with id: {}", savedBook);
            return "Книга успешно добавлена!";
        } catch (Exception e) {
            log.error("Error adding book: {}", book.getTitle(), e);
            throw new RuntimeException(e);
        }
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
        // ПЛОХАЯ ПРАКТИКА.
        //CONTROLLER должен заниматься только отображением
        //        if (id > 1000) {
        //            return ResponseEntity.badRequest().build();
        //        }
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