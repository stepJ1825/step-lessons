package by.step.repository.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceSecond {

    private final GenreRepository genreRepository;

    public GenreServiceSecond(
            @Qualifier("impl1") GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
}
