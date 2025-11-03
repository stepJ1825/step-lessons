package sia.service;

import sia.model.Actors;
import sia.model.Movies;

import java.io.IOException;
import java.util.List;

public interface MoviesService {
    List<Movies> getMoviesFromLastTwoYears();
    List<Actors> getActorsFromMovieByTitle(String title);
    List<Actors> getActorsThatGetActionAtLeastNMovies(int number);
    List<Actors> getActorsWhoAreTheDirectors();
    List<Movies> deleteMoviesThatElderThanNYears(int years) throws IOException;

}
