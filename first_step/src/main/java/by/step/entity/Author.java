package by.step.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
//@NamedQueries(
//        @NamedQuery(
//                name = "Author.findBySurname",
//                query = "SELECT a FROM Author a WHERE a.surname = :surname"
//        )
//)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1. Важно для SERIAL в Postgres
    private Integer id;
    private String firstName;
    private String surname;
}
