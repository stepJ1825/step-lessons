package by.step.repository.impl;

import by.step.model.Author;
import by.step.repository.AuthorRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class AuthorRepositoryImpl implements AuthorRepository {
    private final String data = "src\\main\\resources\\authors.json";
    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public List<Author> getAuthors() {
        List<Author> authors = new ArrayList<>();
        try {
            authors = newMapper().readValue(new File(data),
                    new TypeReference<>() {
                    });
        } catch (IOException e) {
//            log.error("Файл не найден"); //если бы у нас был логгер
            rewriteData(Collections.emptyList());
        }
        return authors;
    }

    @Override
    public void addAuthor(Author author) {
        List<Author> allAuthors = getAuthors();
//        int nextId;
//        if (allAuthors.isEmpty()) {
//            nextId = 1;
//        } else {
//            nextId = allAuthors.get(allAuthors.size() - 1).getId() + 1;
//        }
//        author.setId(nextId);
        allAuthors.add(author); // UnsupportedOperationException
        rewriteData(allAuthors);
    }

    private void rewriteData(List<Author> authors) {
        try {
            newMapper().writeValue(new File(data), authors);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ObjectMapper newMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(df);
        mapper.setLocale(Locale.ENGLISH);
        mapper.registerModule(new JSR310Module());
        return mapper;
    }
}
