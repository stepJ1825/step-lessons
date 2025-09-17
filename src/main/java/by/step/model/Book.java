package by.step.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    //id, title, author, genre, year, rating
    private int id;
    private String title;
    private Author author;
    private Genre genre;
    private int year;
    private float rating;

    public Book(String title, Author author, Genre genre, int year, float rating) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }
}
