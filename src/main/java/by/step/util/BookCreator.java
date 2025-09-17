package by.step.util;

import by.step.model.Author;
import by.step.model.Book;
import by.step.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Stream;

public class BookCreator {

    public static List<Book> generateSampleBooks() {
        return Stream.of(
                        new BookPrototype("1984", "George Orwell", "Dystopian", 1949, 5f),
                        new BookPrototype("To Kill a Mockingbird", "Harper Lee", "Fiction", 1960, 4),
                        new BookPrototype("The Great Gatsby", "F. Scott Fitzgerald", "Classic", 1925, 4),
                        new BookPrototype("Brave New World", "Aldous Huxley", "Dystopian", 1932, 4),
                        new BookPrototype("Pride and Prejudice", "Jane Austen", "Romance", 1813, 5),
                        new BookPrototype("The Catcher in the Rye", "J.D. Salinger", "Fiction", 1951, 4),
                        new BookPrototype("The Lord of the Rings", "J.R.R. Tolkien", "Fantasy", 1954, 5),
                        new BookPrototype("The Hobbit", "J.R.R. Tolkien", "Fantasy", 1937, 5),
                        new BookPrototype("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "Fantasy", 1997, 5),
                        new BookPrototype("The Da Vinci Code", "Dan Brown", "Mystery", 2003, 4),
                        new BookPrototype("The Alchemist", "Paulo Coelho", "Fiction", 1988, 4),
                        new BookPrototype("The Little Prince", "Antoine de Saint-Exupéry", "Fable", 1943, 5),
                        new BookPrototype("The Book Thief", "Markus Zusak", "Historical", 2005, 5),
                        new BookPrototype("The Hunger Games", "Suzanne Collins", "Dystopian", 2008, 4),
                        new BookPrototype("The Kite Runner", "Khaled Hosseini", "Drama", 2003, 5),
                        new BookPrototype("Animal Farm", "George Orwell", "Satire", 1945, 5),
                        new BookPrototype("The Shining", "Stephen King", "Horror", 1977, 4),
                        new BookPrototype("It", "Stephen King", "Horror", 1986, 4),
                        new BookPrototype("The Stand", "Stephen King", "Horror", 1978, 4),
                        new BookPrototype("Gone with the Wind", "Margaret Mitchell", "Historical", 1936, 4),
                        new BookPrototype("War and Peace", "Leo Tolstoy", "Classic", 1869, 5),
                        new BookPrototype("Anna Karenina", "Leo Tolstoy", "Classic", 1877, 5),
                        new BookPrototype("Crime and Punishment", "Fyodor Dostoevsky", "Classic", 1866, 5),
                        new BookPrototype("The Brothers Karamazov", "Fyodor Dostoevsky", "Classic", 1880, 5),
                        new BookPrototype("One Hundred Years of Solitude", "Gabriel García Márquez", "Magical Realism", 1967, 5),
                        new BookPrototype("The Odyssey", "Homer", "Epic", -800, 5),
                        new BookPrototype("The Iliad", "Homer", "Epic", -750, 5),
                        new BookPrototype("Don Quixote", "Miguel de Cervantes", "Satire", 1605, 5),
                        new BookPrototype("Moby Dick", "Herman Melville", "Adventure", 1851, 4),
                        new BookPrototype("The Divine Comedy", "Dante Alighieri", "Epic", 1320, 5),
                        new BookPrototype("The Count of Monte Cristo", "Alexandre Dumas", "Adventure", 1844, 5),
                        new BookPrototype("Les Misérables", "Victor Hugo", "Historical", 1862, 5),
                        new BookPrototype("The Picture of Dorian Gray", "Oscar Wilde", "Gothic", 1890, 4),
                        new BookPrototype("Frankenstein", "Mary Shelley", "Gothic", 1818, 4),
                        new BookPrototype("Dracula", "Bram Stoker", "Gothic", 1897, 4),
                        new BookPrototype("The Adventures of Huckleberry Finn", "Mark Twain", "Adventure", 1884, 4),
                        new BookPrototype("The Adventures of Tom Sawyer", "Mark Twain", "Adventure", 1876, 4),
                        new BookPrototype("Alice's Adventures in Wonderland", "Lewis Carroll", "Fantasy", 1865, 5),
                        new BookPrototype("Through the Looking-Glass", "Lewis Carroll", "Fantasy", 1871, 4),
                        new BookPrototype("The Chronicles of Narnia", "C.S. Lewis", "Fantasy", 1950, 5),
                        new BookPrototype("The Handmaid's Tale", "Margaret Atwood", "Dystopian", 1985, 5),
                        new BookPrototype("Dune", "Frank Herbert", "Science Fiction", 1965, 5),
                        new BookPrototype("Foundation", "Isaac Asimov", "Science Fiction", 1951, 5),
                        new BookPrototype("I, Robot", "Isaac Asimov", "Science Fiction", 1950, 4),
                        new BookPrototype("Neuromancer", "William Gibson", "Cyberpunk", 1984, 4),
                        new BookPrototype("Snow Crash", "Neal Stephenson", "Cyberpunk", 1992, 4),
                        new BookPrototype("The Martian", "Andy Weir", "Science Fiction", 2011, 4),
                        new BookPrototype("Project Hail Mary", "Andy Weir", "Science Fiction", 2021, 5),
                        new BookPrototype("The Name of the Wind", "Patrick Rothfuss", "Fantasy", 2007, 5),
                        new BookPrototype("A Game of Thrones", "George R.R. Martin", "Fantasy", 1996, 5)
                )
                .map(bookPrototype -> {
                            String[] authorSplittedName = bookPrototype.getAuthor().split(" ");
                            String firstName = authorSplittedName[0];
                            String surname = authorSplittedName[authorSplittedName.length - 1];
                            Author author = Author.builder().firstName(firstName).surname(surname).build();
                            return Book.builder()
                                    .title(bookPrototype.getTitle())
                                    .author(author)
                                    .genre(new Genre(0, bookPrototype.getGenre()))
                                    .year(bookPrototype.getYear())
                                    .rating(bookPrototype.getRating())
                                    .build();
                        }
                )
                .toList();

    }

    @Data
    @AllArgsConstructor
    private static class BookPrototype {
        private String title;
        private String author;
        private String genre;
        private int year;
        private float rating;
    }
}
