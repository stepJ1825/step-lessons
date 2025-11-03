package sia;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import sia.model.Movies;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyUtils {
    public static List<Movies> readMovies() {
        ObjectMapper om = new ObjectMapper();

        try (InputStream inputStream = MyUtils.class.getResourceAsStream("/movies.json")) {
            if (inputStream == null) {
                throw new RuntimeException("файл movies.json не обнаружен");
            }
            return om.readValue(inputStream, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("ошибка при чтении файла movies.json.", e);
        }
    }
}
