package by.step.model.jpa;

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
public class AuthorJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1. Важно для SERIAL в Postgres
    private Integer id;
    private String firstName;
    private String surname;
}
