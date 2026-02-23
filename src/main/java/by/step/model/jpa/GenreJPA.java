package by.step.model.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "genres")
public class GenreJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1. Важно для SERIAL в Postgres
    private int id;
    private String name;
}
