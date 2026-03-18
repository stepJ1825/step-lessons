package by.step.controller;

import by.step.common.Book;
import by.step.feign.FeignLibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {
    private final FeignLibraryService libraryService;

    @GetMapping("/books")
    public List<Book> getBooks() {
        return libraryService.getBooks();
    }
}
