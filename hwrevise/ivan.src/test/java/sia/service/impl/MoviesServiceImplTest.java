package sia.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sia.model.Actors;
import sia.model.Movies;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoviesServiceImplTest {
    private MoviesServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new MoviesServiceImpl();
    }

    @Test
    void getMoviesFromLastTwoYearsTest() {
        List<Movies> result = service.getMoviesFromLastTwoYears();
        assertNotNull(result);
    }

    @Test
    void getActorsFromMovieByTitleTest() {
        List<Actors> result = service.getActorsFromMovieByTitle("Oppenheimer");
        assertNotNull(result);
    }

    @Test
    void getActorsThatGetActionAtLeastNMoviesTest() {
        List<Actors> result = service.getActorsThatGetActionAtLeastNMovies(3);
        assertNotNull(result);
    }

    @Test
    void getActorsWhoAreTheDirectorsTest() {
        List<Actors> result = service.getActorsWhoAreTheDirectors();
        assertNotNull(result);
    }

    @Test
    void deleteMoviesThatElderThanNYearsTest() throws IOException {
        List<Movies> result = service.deleteMoviesThatElderThanNYears(39);
        assertNotNull(result);
    }
}