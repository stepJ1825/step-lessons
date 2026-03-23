package by.step.service;

import by.step.entity.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BookFilterTest {

    @Test
    void and_combinesTwoPredicates() {
        BookFilter highRating = book -> book.getRating() >= 4.5f;
        BookFilter yearAfter = book -> book.getReleaseYear() >= 2020;

        BookFilter combined = highRating.and(yearAfter);

        Book book1 = Book.builder().title("A").rating(4.6f).releaseYear(2021).build();
        Book book2 = Book.builder().title("B").rating(4.6f).releaseYear(2019).build();
        Book book3 = Book.builder().title("C").rating(4.4f).releaseYear(2021).build();

        Assertions.assertThat(combined.filter(book1)).isTrue();
        Assertions.assertThat(combined.filter(book2)).isFalse();
        Assertions.assertThat(combined.filter(book3)).isFalse();
    }

    @Test
    void or_combinesTwoPredicates() {
        BookFilter highRating = book -> book.getRating() >= 4.5f;
        BookFilter yearAfter = book -> book.getReleaseYear() >= 2020;

        BookFilter combined = highRating.or(yearAfter);

        Book book1 = Book.builder().title("A").rating(4.6f).releaseYear(2019).build(); // rating ok
        Book book2 = Book.builder().title("B").rating(4.4f).releaseYear(2021).build(); // year ok
        Book book3 = Book.builder().title("C").rating(4.4f).releaseYear(2019).build(); // both fail

        Assertions.assertThat(combined.filter(book1)).isTrue();
        Assertions.assertThat(combined.filter(book2)).isTrue();
        Assertions.assertThat(combined.filter(book3)).isFalse();
    }
}

