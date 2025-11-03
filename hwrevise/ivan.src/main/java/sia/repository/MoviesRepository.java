package sia.repository;

import sia.model.Movies;

import java.io.IOException;
import java.util.List;

public interface MoviesRepository {
    List<Movies> getAllMovies();

    void addMovie(Movies movie) throws IOException;

    void removeMovieByID(int id) throws IOException;

    void saveMovies(List<Movies> movies) throws IOException;
}
