package by.step.service;

import jakarta.annotation.PostConstruct;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class JsonSchemaValidator {

    private Schema schema;

    @PostConstruct
    public void init() throws IOException {
        try (InputStream inputStream = getClass()
                .getResourceAsStream("/schema/city-schema.json")) {
            if (inputStream == null) {
                throw new IOException("Файл схемы не найден: /schema/book-schema.json");
            }
            String schemaContent = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            JSONObject rawSchema = new JSONObject(new JSONTokener(schemaContent));
            this.schema = SchemaLoader.load(rawSchema);
        }
    }

    public void validate(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            schema.validate(jsonObject); // выбрасывает ValidationException при ошибке
        } catch (org.everit.json.schema.ValidationException e) {
            throw new IllegalArgumentException("Ошибка валидации JSON: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new IllegalArgumentException("Некорректный JSON: " + e.getMessage(), e);
        }
    }
}