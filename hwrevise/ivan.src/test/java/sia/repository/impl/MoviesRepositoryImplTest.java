package sia.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sia.model.Movies;
import sia.service.impl.MoviesServiceImpl;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoviesRepositoryImplTest {
    private MoviesRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository = new MoviesRepositoryImpl();
    }

    @Test
    void getAllMoviesTest() {
        List<Movies> result = repository.getAllMovies();
        assertNotNull(result);
    }

    @Test
    void addMovieTest() throws IOException {
        Movies movie = new Movies();
        repository.addMovie(movie);
    }

    @Test
    void removeMovieByIDTest() {
    }

    @Test
    void saveMoviesTest() {
    }
}