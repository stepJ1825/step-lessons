package by.step.controller;

import by.step.dto.BookWithTagsDto;
import by.step.model.Author;
import by.step.model.Book;
import by.step.service.AuthorService;
import by.step.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    // Показывает HTML-страницу со списком книг
    @GetMapping("/books")
    public String booksPage(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books"; // имя шаблона: books.html
    }

    // Обрабатывает добавление новой книги (из формы)
    @PostMapping("/books/add")
    public String addBook(@RequestParam String title, @RequestParam String author) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(Author.builder().firstName(author).build());
        bookService.addBook(book);
        return "redirect:/books"; // перенаправляет обратно на список
    }

    // Показ формы с выпадающими списками
    @GetMapping("/books/create")
    public String showCreateBookForm(Model model) {
        model.addAttribute("bookForm", BookWithTagsDto.builder().build());
        model.addAttribute("authors", authorService.getAuthors());
        return "book-form";
    }

    // Обрабатывает удаление книги
    @PostMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Integer id) {
        bookService.removeBook(id);
        return "redirect:/books";
    }

    @GetMapping("/books/json-demo")
    public String jsonDemo() {
        return "json-demo";
    }
}