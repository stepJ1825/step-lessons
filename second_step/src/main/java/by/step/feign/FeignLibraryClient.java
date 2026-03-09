package by.step.feign;

import by.step.common.Book;
import by.step.common.BookSimpleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@FeignClient(name = "library-service", url = "http://localhost:8082")
public interface FeignLibraryClient {
    @GetMapping("/books")
    List<Book> getBooks();

    @GetMapping("/books")
    List<BookSimpleDto> getBooksSimple();

    @GetMapping("/books")
    String getBooksInString();
}
