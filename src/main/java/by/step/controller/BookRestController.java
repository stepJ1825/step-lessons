package by.step.controller;

import by.step.dto.BookFullDto;
import by.step.model.Book;
import by.step.service.BookService;
import by.step.service.JsonSchemaValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final JsonSchemaValidator jsonSchemaValidator;
    private final ObjectMapper mapper;

    // READ all
    @GetMapping(produces = {"application/json"})
    public List<Book> getAllBooks() {
        //        List<Book> allBooks = bookService.getAllBooks();
        //        allBooks.forEach(book -> bookService.removeBook(book.getId()));
        return bookService.getAllBooks();
    }

    // READ by ID
    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        Book byId = bookService.findById(id);
        return byId != null ? ResponseEntity.ok(byId) :
               ResponseEntity.notFound().build();
    }

    // READ by ID
    @GetMapping(value = "/id", produces = {"application/json"})
    public ResponseEntity<Book> getBookByIdInParam(@RequestParam Integer id) {
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

    // CREATE WITH VALIDATION
    @PostMapping("/validation")
    public ResponseEntity<BookFullDto> createBookWithValidation(@RequestBody String rawJson) {
        // 1. Валидация по JSON Schema
        jsonSchemaValidator.validate(rawJson);

        // 2. Десериализация в DTO
        BookFullDto bookFullDto;
        try {
            bookFullDto = mapper.readValue(rawJson, BookFullDto.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Не удалось преобразовать JSON в BookDto", e);
        }

        // 3. Логика сохранения...
        return ResponseEntity.status(HttpStatus.CREATED).body(bookFullDto);
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