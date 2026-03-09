package by.step.feign;

import by.step.common.Book;
import by.step.common.BookSimpleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeignLibraryService {
    private final FeignLibraryClient libraryClient;

    public List<Book> getBooks() {
       return libraryClient.getBooks();
    }

    public List<BookSimpleDto> getBooksSimple(){
        return libraryClient.getBooksSimple();
    }

    public String getBooksInString(){
        return libraryClient.getBooksInString();
    }
}
