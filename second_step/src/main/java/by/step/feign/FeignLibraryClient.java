package by.step.feign;

import by.step.common.Book;
import by.step.common.BookSimpleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


//@FeignClient(name = "library-service", url = "http://localhost:9876")
@FeignClient("first-step")
public interface FeignLibraryClient {
    @GetMapping("/books")
    List<Book> getBooks();

    @GetMapping("/books-simple-dto")
    List<BookSimpleDto> getBooksSimple();

    @GetMapping("/books-in-string")
    String getBooksInString();


    @GetMapping("/mapped/books")
    List<Book> getBooksMapped();

    @GetMapping("/mapped/books/simple")
    List<BookSimpleDto> getBooksSimpleMapped();

    @GetMapping("/mapped/books/simple-string")
    String getBooksInStringMapped();

}
