package by.step.resttemplate;

import by.step.common.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestTemplateLibraryService {

    private final RestTemplate libraryRestTemplate;


    public List<Book> getAllBooks() {
        ResponseEntity<List<Book>> response = libraryRestTemplate.exchange(
                "/books",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Book>>() {
                }
        );
        return response.getBody() != null ? response.getBody() : Collections.emptyList();

    }
}
