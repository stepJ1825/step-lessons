package by.step.controller;

import by.step.dto.BookFullDto;
import by.step.dto.BookWithTagsDto;
import by.step.dto.BookXMLDto;
import by.step.model.Author;
import by.step.model.Book;
import by.step.model.Genre;
import by.step.service.AuthorService;
import by.step.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JsonAndXmlExampleController {

    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping("/example/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/example/book")
    public Book getExampleBook() {
        Book book = new Book();
        book.setId(1);
        book.setTitle("1984");
        book.setAuthor(new Author(1, "Джордж", "Оруэлл"));
        book.setGenre(new Genre(1, "Антиутопия"));
        book.setYear(1949);
        return book;
    }

    @GetMapping("/example/book-dto")
    public BookWithTagsDto getExampleBookDto() {
        return BookWithTagsDto.builder()
                              .title("1984")
                              .authorId(111L)
                              .genre("Антиутопия")
                              .year(1949)
                              .tagIds(List.of(1L, 123L, 876989L))
                              .build();
    }

    @PostMapping("/example/book-full-dto")
    public void saveBookFullDto(@RequestBody BookFullDto bookFullDto) {
        System.out.println(bookFullDto);
    }

    @GetMapping(value = "/example/book/xml", produces = {"application/xml", "application/json"})
    public BookXMLDto getExampleBookXML() {
        BookXMLDto book = new BookXMLDto();
        book.setId(42L);
        book.setTitle("Мастер и Маргарита");
        book.setAuthor("Михаил Булгаков");
        book.setYear(1967);
        book.setAvailable(true);
        book.setRating(4.9);
        book.setIsbn(null);

        book.setTags(List.of("классика", "фэнтези", "сатира", "философия"));

        BookXMLDto.Publisher pub = new BookXMLDto.Publisher();
        pub.setName("АСТ");
        pub.setCountry("Россия");
        pub.setFounded(1990);
        pub.setActive(true);
        book.setPublisher(pub);

        BookXMLDto.Translation en = new BookXMLDto.Translation();
        en.setLanguage("English");
        en.setTitle("The Master and Margarita");
        en.setTranslator("Richard Pevear");

        BookXMLDto.Translation fr = new BookXMLDto.Translation();
        fr.setLanguage("Français");
        fr.setTitle("Le Maître et Marguerite");
        fr.setTranslator("Serguei Chotkine");

        book.setTranslations(List.of(en, fr));

        BookXMLDto.Metadata meta = new BookXMLDto.Metadata();
        meta.setPages(480);
        meta.setDimensions(List.of(14.5, 21.0, 2.8));
        meta.setHasIllustrations(false);
        meta.setReviews(List.of()); // пустой список
        book.setMetadata(meta);

        return book;
    }

}