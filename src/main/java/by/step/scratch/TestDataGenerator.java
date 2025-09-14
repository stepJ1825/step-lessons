package by.step.scratch;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestDataGenerator {

    public static List<Book> generateSampleBooks() {
        return Arrays.asList(
                new Book(1, "1984", "George Orwell", "Dystopian", 1949, 5),
                new Book(2, "To Kill a Mockingbird", "Harper Lee", "Fiction", 1960, 4),
                new Book(3, "The Great Gatsby", "F. Scott Fitzgerald", "Classic", 1925, 4),
                new Book(4, "Brave New World", "Aldous Huxley", "Dystopian", 1932, 4),
                new Book(5, "Pride and Prejudice", "Jane Austen", "Romance", 1813, 5),
                new Book(6, "The Catcher in the Rye", "J.D. Salinger", "Fiction", 1951, 4),
                new Book(7, "The Lord of the Rings", "J.R.R. Tolkien", "Fantasy", 1954, 5),
                new Book(8, "The Hobbit", "J.R.R. Tolkien", "Fantasy", 1937, 5),
                new Book(9, "Harry Potter and the Philosopher's Stone", "J.K. Rowling", "Fantasy", 1997, 5),
                new Book(10, "The Da Vinci Code", "Dan Brown", "Mystery", 2003, 4),
                new Book(11, "The Alchemist", "Paulo Coelho", "Fiction", 1988, 4),
                new Book(12, "The Little Prince", "Antoine de Saint-Exupéry", "Fable", 1943, 5),
                new Book(13, "The Book Thief", "Markus Zusak", "Historical", 2005, 5),
                new Book(14, "The Hunger Games", "Suzanne Collins", "Dystopian", 2008, 4),
                new Book(15, "The Kite Runner", "Khaled Hosseini", "Drama", 2003, 5),
                new Book(16, "Animal Farm", "George Orwell", "Satire", 1945, 5),
                new Book(17, "The Shining", "Stephen King", "Horror", 1977, 4),
                new Book(18, "It", "Stephen King", "Horror", 1986, 4),
                new Book(19, "The Stand", "Stephen King", "Horror", 1978, 4),
                new Book(20, "Gone with the Wind", "Margaret Mitchell", "Historical", 1936, 4),
                new Book(21, "War and Peace", "Leo Tolstoy", "Classic", 1869, 5),
                new Book(22, "Anna Karenina", "Leo Tolstoy", "Classic", 1877, 5),
                new Book(23, "Crime and Punishment", "Fyodor Dostoevsky", "Classic", 1866, 5),
                new Book(24, "The Brothers Karamazov", "Fyodor Dostoevsky", "Classic", 1880, 5),
                new Book(25, "One Hundred Years of Solitude", "Gabriel García Márquez", "Magical Realism", 1967, 5),
                new Book(26, "The Odyssey", "Homer", "Epic", -800, 5),
                new Book(27, "The Iliad", "Homer", "Epic", -750, 5),
                new Book(28, "Don Quixote", "Miguel de Cervantes", "Satire", 1605, 5),
                new Book(29, "Moby Dick", "Herman Melville", "Adventure", 1851, 4),
                new Book(30, "The Divine Comedy", "Dante Alighieri", "Epic", 1320, 5),
                new Book(31, "The Count of Monte Cristo", "Alexandre Dumas", "Adventure", 1844, 5),
                new Book(32, "Les Misérables", "Victor Hugo", "Historical", 1862, 5),
                new Book(33, "The Picture of Dorian Gray", "Oscar Wilde", "Gothic", 1890, 4),
                new Book(34, "Frankenstein", "Mary Shelley", "Gothic", 1818, 4),
                new Book(35, "Dracula", "Bram Stoker", "Gothic", 1897, 4),
                new Book(36, "The Adventures of Huckleberry Finn", "Mark Twain", "Adventure", 1884, 4),
                new Book(37, "The Adventures of Tom Sawyer", "Mark Twain", "Adventure", 1876, 4),
                new Book(38, "Alice's Adventures in Wonderland", "Lewis Carroll", "Fantasy", 1865, 5),
                new Book(39, "Through the Looking-Glass", "Lewis Carroll", "Fantasy", 1871, 4),
                new Book(40, "The Chronicles of Narnia", "C.S. Lewis", "Fantasy", 1950, 5),
                new Book(41, "The Handmaid's Tale", "Margaret Atwood", "Dystopian", 1985, 5),
                new Book(42, "Dune", "Frank Herbert", "Science Fiction", 1965, 5),
                new Book(43, "Foundation", "Isaac Asimov", "Science Fiction", 1951, 5),
                new Book(44, "I, Robot", "Isaac Asimov", "Science Fiction", 1950, 4),
                new Book(45, "Neuromancer", "William Gibson", "Cyberpunk", 1984, 4),
                new Book(46, "Snow Crash", "Neal Stephenson", "Cyberpunk", 1992, 4),
                new Book(47, "The Martian", "Andy Weir", "Science Fiction", 2011, 4),
                new Book(48, "Project Hail Mary", "Andy Weir", "Science Fiction", 2021, 5),
                new Book(49, "The Name of the Wind", "Patrick Rothfuss", "Fantasy", 2007, 5),
                new Book(50, "A Game of Thrones", "George R.R. Martin", "Fantasy", 1996, 5)
        );
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

        String[] authors = {
                "John Smith", "Emily Johnson", "Michael Brown", "Sarah Williams",
                "David Jones", "Jennifer Miller", "Robert Davis", "Lisa Garcia",
                "Daniel Wilson", "Maria Anderson", "Paul Taylor", "Karen Thomas",
                "Mark Jackson", "Nancy White", "Charles Harris", "Susan Martin",
                "Thomas Thompson", "Margaret Moore", "Christopher Lee", "Jessica Clark"
        };

        String[] genres = {
                "Fiction", "Fantasy", "Science Fiction", "Mystery", "Romance",
                "Thriller", "Horror", "Historical", "Biography", "Adventure",
                "Dystopian", "Classic", "Young Adult", "Children", "Poetry"
        };

        Book[] books = new Book[count];
        for (int i = 0; i < count; i++) {
            String title = titles[random.nextInt(titles.length)] + " " + (random.nextInt(5) + 1);
            String author = authors[random.nextInt(authors.length)];
            String genre = genres[random.nextInt(genres.length)];
            int year = 1900 + random.nextInt(124); // 1900-2024
            int rating = 1 + random.nextInt(5); // 1-5

            books[i] = new Book(i + 1, title, author, genre, year, rating);
        }

        return Arrays.asList(books);
    }
}

class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private int year;
    private int rating;

    public Book(int id, String title, String author, String genre, int year, int rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public int getYear() { return year; }
    public int getRating() { return rating; }

    @Override
    public String toString() {
        return String.format("%d. %s by %s (%s, %d) %s",
                id, title, author, genre, year, "★".repeat(rating) + "☆".repeat(5 - rating));
    }
}
