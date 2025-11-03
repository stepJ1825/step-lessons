package sia;

import org.junit.jupiter.api.Test;
import sia.model.Movies;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyUtilsTest {

    @Test
    void readMoviesTest() {
        List<Movies> movies = MyUtils.readMovies();
        assertNotNull(movies);
    }
}