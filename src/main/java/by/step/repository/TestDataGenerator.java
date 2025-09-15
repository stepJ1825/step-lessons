package by.step.repository;

import by.step.model.Author;
import by.step.model.Book;
import by.step.model.Genre;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class TestDataGenerator {

//    public static List<Book> generateSampleBooks() {
//        return Arrays.asList(
//                new Book("1984", "George Orwell", "Dystopian", 1949, 5f),
//                new Book("To Kill a Mockingbird", "Harper Lee", "Fiction", 1960, 4),
//                new Book("The Great Gatsby", "F. Scott Fitzgerald", "Classic", 1925, 4),
//                new Book("Brave New World", "Aldous Huxley", "Dystopian", 1932, 4),
//                new Book("Pride and Prejudice", "Jane Austen", "Romance", 1813, 5),
//                new Book("The Catcher in the Rye", "J.D. Salinger", "Fiction", 1951, 4),
//                new Book("The Lord of the Rings", "J.R.R. Tolkien", "Fantasy", 1954, 5),
//                new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", 1937, 5),
//                new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "Fantasy", 1997, 5),
//                new Book("The Da Vinci Code", "Dan Brown", "Mystery", 2003, 4),
//                new Book("The Alchemist", "Paulo Coelho", "Fiction", 1988, 4),
//                new Book("The Little Prince", "Antoine de Saint-Exupéry", "Fable", 1943, 5),
//                new Book("The Book Thief", "Markus Zusak", "Historical", 2005, 5),
//                new Book("The Hunger Games", "Suzanne Collins", "Dystopian", 2008, 4),
//                new Book("The Kite Runner", "Khaled Hosseini", "Drama", 2003, 5),
//                new Book("Animal Farm", "George Orwell", "Satire", 1945, 5),
//                new Book("The Shining", "Stephen King", "Horror", 1977, 4),
//                new Book("It", "Stephen King", "Horror", 1986, 4),
//                new Book("The Stand", "Stephen King", "Horror", 1978, 4),
//                new Book("Gone with the Wind", "Margaret Mitchell", "Historical", 1936, 4),
//                new Book("War and Peace", "Leo Tolstoy", "Classic", 1869, 5),
//                new Book("Anna Karenina", "Leo Tolstoy", "Classic", 1877, 5),
//                new Book("Crime and Punishment", "Fyodor Dostoevsky", "Classic", 1866, 5),
//                new Book("The Brothers Karamazov", "Fyodor Dostoevsky", "Classic", 1880, 5),
//                new Book("One Hundred Years of Solitude", "Gabriel García Márquez", "Magical Realism", 1967, 5),
//                new Book("The Odyssey", "Homer", "Epic", -800, 5),
//                new Book("The Iliad", "Homer", "Epic", -750, 5),
//                new Book("Don Quixote", "Miguel de Cervantes", "Satire", 1605, 5),
//                new Book("Moby Dick", "Herman Melville", "Adventure", 1851, 4),
//                new Book("The Divine Comedy", "Dante Alighieri", "Epic", 1320, 5),
//                new Book("The Count of Monte Cristo", "Alexandre Dumas", "Adventure", 1844, 5),
//                new Book("Les Misérables", "Victor Hugo", "Historical", 1862, 5),
//                new Book("The Picture of Dorian Gray", "Oscar Wilde", "Gothic", 1890, 4),
//                new Book("Frankenstein", "Mary Shelley", "Gothic", 1818, 4),
//                new Book("Dracula", "Bram Stoker", "Gothic", 1897, 4),
//                new Book("The Adventures of Huckleberry Finn", "Mark Twain", "Adventure", 1884, 4),
//                new Book("The Adventures of Tom Sawyer", "Mark Twain", "Adventure", 1876, 4),
//                new Book("Alice's Adventures in Wonderland", "Lewis Carroll", "Fantasy", 1865, 5),
//                new Book("Through the Looking-Glass", "Lewis Carroll", "Fantasy", 1871, 4),
//                new Book("The Chronicles of Narnia", "C.S. Lewis", "Fantasy", 1950, 5),
//                new Book("The Handmaid's Tale", "Margaret Atwood", "Dystopian", 1985, 5),
//                new Book("Dune", "Frank Herbert", "Science Fiction", 1965, 5),
//                new Book("Foundation", "Isaac Asimov", "Science Fiction", 1951, 5),
//                new Book("I, Robot", "Isaac Asimov", "Science Fiction", 1950, 4),
//                new Book("Neuromancer", "William Gibson", "Cyberpunk", 1984, 4),
//                new Book("Snow Crash", "Neal Stephenson", "Cyberpunk", 1992, 4),
//                new Book("The Martian", "Andy Weir", "Science Fiction", 2011, 4),
//                new Book("Project Hail Mary", "Andy Weir", "Science Fiction", 2021, 5),
//                new Book("The Name of the Wind", "Patrick Rothfuss", "Fantasy", 2007, 5),
//                new Book("A Game of Thrones", "George R.R. Martin", "Fantasy", 1996, 5)
//        );
//    }

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

        return Arrays.asList(books);
    }
}

