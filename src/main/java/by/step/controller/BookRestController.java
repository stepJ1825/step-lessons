package by.step.controller;

import by.step.model.Book;
import by.step.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/books")
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    // READ all
    @GetMapping
    public List<Book> getAllBooks() {
//        List<Book> allBooks = bookService.getAllBooks();
//        allBooks.forEach(book -> bookService.removeBook(book.getId()));
        return bookService.getAllBooks();
    }

    // READ by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
//        throw new RuntimeException();
        Book byId = bookService.findById(id);
        return byId != null ? ResponseEntity.ok(byId) :
               ResponseEntity.notFound().build();
    }

    // READ by ID
    @GetMapping("/id")
    public ResponseEntity<Book> getBookByIdInParam(@RequestParam Integer id) {
//        throw new RuntimeException();
        Book byId = bookService.findById(id);
        return byId != null ? ResponseEntity.ok(byId) :
                ResponseEntity.notFound().build();
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    // UPDATE //TODO
    //    @PutMapping("/{id}")
    //    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
    //        Book updatedBook = bookService.updateBook(id, bookDetails);
    //        return ResponseEntity.ok(updatedBook);
    //    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        bookService.removeBook(id);
        return ResponseEntity.noContent().build();
    }

}