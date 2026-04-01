package by.step.feign;

import by.step.dto.BookFullDto;
import by.step.dto.BookSimpleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


//@FeignClient(name = "library-service", url = "http://localhost:9876")
@FeignClient("first-step")
public interface FeignLibraryClient {
    @GetMapping("/books")
    List<BookFullDto> getBooks();

    @GetMapping("/books-simple-dto")
    List<BookSimpleDTO> getBooksSimple();

    @GetMapping("/books-in-string")
    String getBooksInString();


    @GetMapping("/mapped/books")
    List<BookFullDto> getBooksMapped();

    @GetMapping("/mapped/books/simple")
    List<BookSimpleDTO> getBooksSimpleMapped();

    @GetMapping("/mapped/books/simple-string")
    String getBooksInStringMapped();

}
