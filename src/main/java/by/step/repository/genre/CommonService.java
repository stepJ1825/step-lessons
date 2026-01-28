package by.step.repository.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonService {

    private final GenreService oneGenreService;
    private final GenreService secondGenreService;
    private final List<GenreService> serviceList;

    public CommonService(
            @Qualifier("genreService3") GenreService oneGenreService, GenreService secondGenreService,
            List<GenreService> serviceList) {
        this.oneGenreService = oneGenreService;
        this.secondGenreService = secondGenreService;
        this.serviceList = serviceList;
    }
}
