package by.step.controller;

import by.step.dto.BookCreateDTO;
import by.step.dto.BookResponseDTO;
import by.step.dto.BookSimpleDTO;
import by.step.entity.Book;
import by.step.mapper.BookMapper;
import by.step.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/mapped/books")
@RequiredArgsConstructor
public class BookMapperRestController {

    private final BookService bookService;
    private final BookMapper bookMapper; // Инжектируем маппер

    @PostMapping
    public BookResponseDTO addBook(@RequestBody BookCreateDTO bookCreateDTO) {
        log.info("Received request to add new book: {}", bookCreateDTO.getTitle());
        try{
            Book book = bookMapper.toEntity(bookCreateDTO);
            Book savedBook = bookService.addBook(book);
            log.info("Successfully added book with id: {}", savedBook.getId());
            return bookMapper.toResponseDTO(savedBook);
        } catch (Exception e) {
            log.error("Error adding book: {}", bookCreateDTO.getTitle(), e);
            throw new RuntimeException(e);
        }

    }

    // Альтернативный вариант с ответом DTO
    @PostMapping("/with-response")
    public ResponseEntity<BookResponseDTO> addBookWithResponse(@RequestBody BookCreateDTO bookCreateDTO) {
        Book book = bookMapper.toEntity(bookCreateDTO);
        Book savedBook = bookService.addBook(book);
        return ResponseEntity.ok(bookMapper.toResponseDTO(savedBook));
    }

    @DeleteMapping("/{id}")
    public String removeBook(@PathVariable int id) {
        bookService.removeBook(id);
        return "Книга удалена (если существовала)";
    }

    @GetMapping
    public List<BookResponseDTO> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return bookMapper.toResponseDTOList(books);
    }

    // Эндпоинт для получения SimpleDTO (используется другим сервисом)
    @GetMapping("/simple")
    public List<BookSimpleDTO> getSimpleBooks() {
        List<Book> books = bookService.getAllBooks();
        return bookMapper.toSimpleDTOList(books);
    }

    @GetMapping("/simple-string")
    public String getSimpleBooksAsString() {
        List<BookSimpleDTO> books = bookMapper.toSimpleDTOList(bookService.getAllBooks());
        return books.toString();
    }

    @GetMapping("/author/{authorSurname}")
    public List<BookResponseDTO> findBooksByAuthor(@PathVariable String authorSurname) {
        List<Book> books = bookService.findBooksByAuthor(authorSurname);
        return bookMapper.toResponseDTOList(books);
    }

    @GetMapping("/average-rating")
    public float getAverageRating() {
        return bookService.getAverageRating();
    }

    @GetMapping("/grouped-by-genre")
    public Map<String, List<BookResponseDTO>> getBooksGroupedByGenre() {
        Map<String, List<Book>> groupedByGenre = bookService.getBooksGroupedByGenre();
        // Преобразуем каждую группу в DTO
        return groupedByGenre.entrySet().stream()
                             .collect(Collectors.toMap(
                                     Map.Entry::getKey,
                                     entry -> bookMapper.toResponseDTOList(entry.getValue())
                             ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> findById(@PathVariable("id") int id) {
        log.debug("Searching for book with id: {}", id);
        if (id > 1000) {
            log.warn("Invalid book id requested: {} (exceeds maximum allowed)", id);
            return ResponseEntity.badRequest().build();
        }
        Book book = bookService.findById(id);
        if (book == null) {
            log.warn("Book with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        log.debug("Found book: {} by {}", book.getTitle(), book.getAuthor().getSurname());
        return ResponseEntity.ok(bookMapper.toResponseDTO(book));
    }

    @GetMapping("/param-id")
    public BookResponseDTO findByIdInParam(@RequestParam(name = "id", required = false) Integer id) {
        if (id == null) {
            return null;
        }
        Book book = bookService.findById(id);
        return bookMapper.toResponseDTO(book);
    }

    @GetMapping("/search")
    public List<BookResponseDTO> searchBooks(@RequestParam String keyword) {
        List<Book> books = bookService.searchBooks(keyword);
        return bookMapper.toResponseDTOList(books);
    }

    @GetMapping("/titles")
    public String getBookTitlesAsString() {
        return bookService.getBookTitlesAsString();
    }

    @GetMapping("/author-statistics")
    public Map<String, Serializable> getAuthorStatistics(@RequestParam String authorName) {
        return bookService.getAuthorStatistics(authorName);
    }

    // Пример обновления книги
    @PutMapping("/{id}")
    public BookResponseDTO updateBook(@PathVariable int id, @RequestBody BookCreateDTO bookCreateDTO) {
        Book existingBook = bookService.findById(id);
        bookMapper.updateBookFromDTO(bookCreateDTO, existingBook);
        Book updatedBook = bookService.updateBook(existingBook);
        return bookMapper.toResponseDTO(updatedBook);
    }
}