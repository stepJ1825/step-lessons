package by.step.feign;

import by.step.common.Book;
import by.step.common.BookSimpleDto;
import by.step.common.DifferentBookDto;
import by.step.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeignLibraryService {
    private final FeignLibraryClient libraryClient;
    private final BookMapper bookMapper;


    public List<Book> getBooks() {
        return libraryClient.getBooks();
    }

    public List<BookSimpleDto> getBooksSimple() {
        return libraryClient.getBooksSimple();
    }

    public String getBooksInString() {
        return libraryClient.getBooksInString();
    }


    public List<BookSimpleDto> getBooksAsSimpleDto() {
        List<Book> books = libraryClient.getBooksMapped();
        return bookMapper.toSimpleDtoList(books);
    }

    public List<BookSimpleDto> getBooksFromSimpleEndpoint() {
        // Если первый сервис уже отдает SimpleDTO
        return libraryClient.getBooksSimpleMapped();
    }

    public BookSimpleDto getFirstBook() {
        List<Book> books = libraryClient.getBooksMapped();
        return books.isEmpty() ? null : bookMapper.toSimpleDto(books.get(0));
    }

    public String getBooksInStringMapped() {
        return libraryClient.getBooksInStringMapped();
    }

    public DifferentBookDto getDifferentBookDto() {
        Book book = getBooks().get(0);
        return bookMapper.toDifferentBookDto(book);
    }

}
