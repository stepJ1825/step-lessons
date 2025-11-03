package sia.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import sia.MyUtils;
import sia.model.Movies;
import sia.repository.MoviesRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MoviesRepositoryImpl implements MoviesRepository {
    private final String DATA = "src/main/resources/movies-deleted.json";
    private final ObjectMapper om = new ObjectMapper();

    @Override
    public List<Movies> getAllMovies() {
        return MyUtils.readMovies();
    }

    @Override
    public void addMovie(Movies movie) throws IOException {
        List<Movies> movies = getAllMovies();
        movies.add(movie);
        saveMovies(movies);
    }

    @Override
    public void removeMovieByID(int id) throws IOException {
        List<Movies> movies = getAllMovies();
        movies.removeIf(movies1 -> movies1.getId() == id);
        saveMovies(movies);
    }

    public void saveMovies(List<Movies> movies) throws IOException {
        om.writerWithDefaultPrettyPrinter().writeValue(new File(DATA), movies);
    }

    public MoviesRepositoryImpl() {
        getAllMovies();
    }
}


