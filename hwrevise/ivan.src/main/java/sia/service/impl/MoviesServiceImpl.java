package sia.service.impl;

import sia.model.Actors;
import sia.model.Directors;
import sia.model.Movies;
import sia.repository.MoviesRepository;
import sia.repository.impl.MoviesRepositoryImpl;
import sia.service.MoviesService;

import java.io.IOException;
import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MoviesServiceImpl implements MoviesService {
    public final MoviesRepository repository;

    public MoviesServiceImpl() {
        this.repository = new MoviesRepositoryImpl();
    }


    @Override
    public List<Movies> getMoviesFromLastTwoYears() {
        int currentYear = Year.now().getValue();
        return repository.getAllMovies()
                .stream()
                .filter(movies -> movies.getReleaseYear() == currentYear
                        || movies.getReleaseYear() == currentYear - 1)
                .collect(Collectors.toList());
    }

    @Override
    public List<Actors> getActorsFromMovieByTitle(String title) {
        return repository.getAllMovies()
                .stream()
                .filter(movies -> movies.getTitle()
                        .equalsIgnoreCase(title))
                .flatMap(movies -> movies.getActors().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Actors> getActorsThatGetActionAtLeastNMovies(int number) {
        Map<Actors, Long> actorCount = repository.getAllMovies()
                .stream()
                .flatMap(movies -> movies.getActors()
                        .stream())
                .collect(Collectors.groupingBy(actors -> actors, Collectors.counting()));

        return actorCount.entrySet().stream()
                .filter(actorsLongEntry -> actorsLongEntry.getValue() >= number)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public List<Actors> getActorsWhoAreTheDirectors() {
        Set<String> directorsName = repository.getAllMovies().stream()
                .flatMap(movies -> movies.getDirectors().stream())
                .map(Directors::getFullName)
                .collect(Collectors.toSet());

        return repository.getAllMovies().stream()
                .flatMap(movies -> movies.getActors().stream())
                .filter(actors -> directorsName.contains(actors.getFullName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Movies> deleteMoviesThatElderThanNYears(int years) throws IOException {
        int removedYears = Year.now().getValue() - years;
        List<Movies> filteredMovies = repository.getAllMovies().stream()
                .filter(movies -> movies.getReleaseYear() >= removedYears)
                .toList();
        repository.saveMovies(filteredMovies);

        return filteredMovies;
    }
}
