package by.step.repository;

import by.step.model.Author;
import by.step.model.Book;
import by.step.model.Genre;
import by.step.util.BookCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class TestDataGenerator {

    public static List<Book> generateSampleBooks() {
        return BookCreator.generateSampleBooks();
    }

    public static List<Book> generateRandomBooks(int count) {
        Random random = new Random();
        String[] titles = {
                "The Silent Ocean", "Eternal Dreams", "Whispering Wind", "Golden Horizon",
                "Midnight Shadows", "Crimson Dawn", "Silver Moon", "Ancient Secrets",
                "Forgotten Realm", "Eternal Flame", "Lost Kingdom", "Hidden Truth",
                "Final Destination", "Endless Journey", "Mystic River", "Dark Forest",
                "Bright Future", "Last Hope", "First Light", "Winter's Tale"
        };

        String[] authorsInString = {
                "John Smith", "Emily Johnson", "Michael Brown", "Sarah Williams",
                "David Jones", "Jennifer Miller", "Robert Davis", "Lisa Garcia",
                "Daniel Wilson", "Maria Anderson", "Paul Taylor", "Karen Thomas",
                "Mark Jackson", "Nancy White", "Charles Harris", "Susan Martin",
                "Thomas Thompson", "Margaret Moore", "Christopher Lee", "Jessica Clark"
        };

        Author[] authors = Arrays.stream(authorsInString)
                .map(s -> Author.builder()
                        .firstName(s.split(" ")[0])
                        .surname(s.split(" ")[1])
                        .build())
                .toArray(Author[]::new);

        Genre[] genres = Stream.of(
                        "Fiction", "Fantasy", "Science Fiction", "Mystery", "Romance",
                        "Thriller", "Horror", "Historical", "Biography", "Adventure",
                        "Dystopian", "Classic", "Young Adult", "Children", "Poetry")
                .map(s -> new Genre(0, s))
                .toArray(Genre[]::new);

        Book[] books = new Book[count];
        for (int i = 0; i < count; i++) {
            String title = titles[random.nextInt(titles.length)] + " " + (random.nextInt(5) + 1);
            Author author = authors[random.nextInt(authors.length)];
            Genre genre = genres[random.nextInt(genres.length)];
            int year = 1900 + random.nextInt(124); // 1900-2024
            int rating = 1 + random.nextInt(5); // 1-5

            books[i] = Book.builder()
                    .title(title)
                    .author(author)
                    .genre(genre)
                    .year(year)
                    .rating(rating)
                    .build();
        }

        return new ArrayList<>(Arrays.asList(books));
    }
}

