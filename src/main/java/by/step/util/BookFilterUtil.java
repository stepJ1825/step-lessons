package by.step.util;

import by.step.model.Author;
import by.step.service.BookFilter;

import java.util.Arrays;

public class BookFilterUtil {
    public static BookFilter getByAuthor(String author) {
        return book -> {
            Author author1 = book.getAuthor();
            return author1.getFirstName().equalsIgnoreCase(author)
                   || author1.getSurname().equalsIgnoreCase(author);
        };
    }

    public static BookFilter getByTitle(String titleWord) {
        return book -> Arrays.stream(book.getTitle()
                                         .split(" "))
                             .anyMatch(s -> s.equalsIgnoreCase(titleWord));
    }

    public static BookFilter getByKeyword(String keyWord) {
        BookFilter byTitle = getByTitle(keyWord);
        BookFilter byAuthor = getByAuthor(keyWord);
        return byTitle.or(byAuthor);
    }

    public static BookFilter getByPartOfTitle(String partOfTitleWord) {
        return book -> book.getTitle().toUpperCase()
                           .contains(partOfTitleWord.toUpperCase());
    }

    public static BookFilter byGenre(String genre) {
        throw new IllegalArgumentException("not implemented");
    }

    public static BookFilter byMinRating(int minRating) {
        throw new IllegalArgumentException("not implemented");
    }

    public static BookFilter byMaxRating(int maxRating) {
        throw new IllegalArgumentException("not implemented");
    }

    //Классические книги (до 1950, рейтинг >=4)
    public static BookFilter isClassic() {
        throw new IllegalArgumentException("not implemented");
    }

    //Бестселлеры (рейтинг 5)
    public static BookFilter isBestseller() {
        throw new IllegalArgumentException("not implemented");
    }
}
