package by.step.repository.impl;

import by.step.model.Author;
import by.step.repository.AuthorRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
@Data
public class AuthorRepositoryImpl implements AuthorRepository {
    @Value("#{'${data.json}'.split(',')[1]}")
    private String data;
    @Autowired
    @Qualifier("dateFormatter")
    private SimpleDateFormat df;

    private AuthorRepositoryImpl() {
        System.out.println("some");
    }

    @PostConstruct
    public void init() {
        data = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(data)).getPath();
    }

    @Override
    public List<Author> getAuthors() {
        List<Author> authors = new ArrayList<>();
        try {
            authors = newMapper().readValue(new File(data),
                    new TypeReference<>() {
                    });
        } catch (IOException e) {
            rewriteData(Collections.emptyList());
        }
        return authors;
    }

    @Override
    public void addAuthor(Author author) {
        List<Author> allAuthors = getAuthors();
        allAuthors.add(author);
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
