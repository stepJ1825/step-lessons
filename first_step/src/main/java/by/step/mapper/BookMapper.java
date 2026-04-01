package by.step.mapper;

import by.step.dto.BookCreateDTO;
import by.step.dto.BookResponseDTO;
import by.step.dto.BookSimpleDTO;
import by.step.entity.Author;
import by.step.entity.Book;
import by.step.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    // 1. Основной маппинг с displayInfo
    @Mapping(target = "authorFullName", expression = "java(getAuthorFullName(book.getAuthor()))")
    @Mapping(target = "genreName", source = "genre.name")
    @Mapping(target = "displayInfo", expression = "java(book.getTitle() + \" (\" + book.getReleaseYear() + \", рейтинг: \" + book.getRating() + \")\")")
    BookResponseDTO toResponseDTO(Book book);

    // 2. Маппинг из DTO в Entity с обработкой связей
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", source = "authorId", qualifiedByName = "mapAuthor")
    @Mapping(target = "genre", source = "genreId", qualifiedByName = "mapGenre")
    Book toEntity(BookCreateDTO bookCreateDTO);

    // 3. Обновление существующей сущности из DTO
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", source = "authorId", qualifiedByName = "mapAuthor")
    @Mapping(target = "genre", source = "genreId", qualifiedByName = "mapGenre")
    void updateBookFromDTO(BookCreateDTO dto, @MappingTarget Book book);

    // 4. Маппинг в SimpleDTO для передачи в другие сервисы
    BookSimpleDTO toSimpleDTO(Book book);

    // 5. Маппинг списков
    List<BookResponseDTO> toResponseDTOList(List<Book> books);
    List<BookSimpleDTO> toSimpleDTOList(List<Book> books);

    // 6. Кастомные методы для сложной логики
    default String getAuthorFullName(Author author) {
        if (author == null) return "Unknown";
        return author.getFirstName() + " " + author.getSurname();
    }

    @Named("mapAuthor")
    default Author mapAuthor(Integer authorId) {
        if (authorId == null) return null;
        return Author.builder()
                     .id(authorId)
                     .build();
    }

    @Named("mapGenre")
    default Genre mapGenre(Integer genreId) {
        if (genreId == null) return null;
        return Genre.builder()
                    .id(genreId)
                    .build();
    }
}