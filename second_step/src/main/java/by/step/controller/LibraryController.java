package by.step.controller;

import by.step.common.BookSimpleDto;
import by.step.common.DifferentBookDto;
import by.step.feign.FeignLibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class LibraryController {

    private final FeignLibraryService libraryService;

    @GetMapping("/books")
    public List<BookSimpleDto> getBooks() {
        return libraryService.getBooksAsSimpleDto();
    }

    @GetMapping("/books-string")
    public String getBooksString() {
        return libraryService.getBooksInStringMapped();
    }

    @GetMapping("/first-book")
    public BookSimpleDto getFirstBook() {
        return libraryService.getFirstBook();
    }

    @GetMapping("/different")
    public DifferentBookDto getDifferentBookDto() {
        return libraryService.getDifferentBookDto();
    }

}