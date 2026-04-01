package by.step.feign;

import by.step.common.DifferentBookDto;
import by.step.dto.BookFullDto;
import by.step.dto.BookSimpleDTO;
import by.step.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeignLibraryService {
    private final FeignLibraryClient libraryClient;
    private final BookMapper bookMapper;


    public List<BookFullDto> getBooks() {
        return libraryClient.getBooks();
    }

    public List<BookSimpleDTO> getBooksSimple() {
        return libraryClient.getBooksSimple();
    }

    public String getBooksInString() {
        return libraryClient.getBooksInString();
    }


    public List<BookSimpleDTO> getBooksAsSimpleDto() {
        List<BookFullDto> books = libraryClient.getBooksMapped();
        return bookMapper.toSimpleDtoList(books);
    }

    public List<BookSimpleDTO> getBooksFromSimpleEndpoint() {
        // Если первый сервис уже отдает SimpleDTO
        return libraryClient.getBooksSimpleMapped();
    }

    public BookSimpleDTO getFirstBook() {
        List<BookFullDto> books = libraryClient.getBooksMapped();
        return books.isEmpty() ? null : bookMapper.toSimpleDto(books.get(0));
    }

    public String getBooksInStringMapped() {
        return libraryClient.getBooksInStringMapped();
    }

    public DifferentBookDto getDifferentBookDto() {
        BookFullDto book = getBooks().get(0);
        return bookMapper.toDifferentBookDto(book);
    }

}
