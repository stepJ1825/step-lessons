package by.step.mapper;

import by.step.common.Author;
import by.step.common.Book;
import by.step.common.BookSimpleDto;
import by.step.common.DifferentBookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    // 1. Простой маппинг из Book в BookSimpleDto
    BookSimpleDto toSimpleDto(Book book);

    // 2. Маппинг списка
    List<BookSimpleDto> toSimpleDtoList(List<Book> books);

    // 3. Обратный маппинг если нужно
    @Mapping(target = "id", source = "id") // явно указываем маппинг
    @Mapping(target = "title", source = "title")
    @Mapping(target = "author", ignore = true) // игнорируем поля, которых нет в DTO
    @Mapping(target = "genre", ignore = true)
    @Mapping(target = "releaseYear", ignore = true)
    @Mapping(target = "rating", ignore = true)
    Book toEntity(BookSimpleDto bookSimpleDto);

    @Mapping(target = "id", source = "id") // явно указываем маппинг
    @Mapping(target = "authorFullName", source = "author", qualifiedByName = "mapAuthorFullName")
    DifferentBookDto toDifferentBookDto(Book book);

    @Named("mapAuthorFullName")
    default String getAuthorFullName(Author author) {
        if (author == null) return "Unknown";
        return author.getFirstName() + " " + author.getSurname();
    }

}