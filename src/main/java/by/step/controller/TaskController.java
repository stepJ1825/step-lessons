package by.step.controller;

import by.step.dto.BookFullDto;
import by.step.service.BookService;
import by.step.service.JsonSchemaValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("task")
@RequiredArgsConstructor
public class TaskController {

    private final JsonSchemaValidator jsonSchemaValidator;
    private final ObjectMapper mapper;

    // CREATE WITH VALIDATION
    @PostMapping
    public ResponseEntity<String> validationTask(@RequestBody String rawJson) throws JsonProcessingException {
        // 1. Валидация по JSON Schema
        try {
            jsonSchemaValidator.validate(rawJson);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        // 2. Десериализация в DTO
        BookFullDto bookFullDto;        //TODO: Заменить на свой класс
        try {
            bookFullDto = mapper.readValue(rawJson, BookFullDto.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Не удалось преобразовать JSON в BookDto", e);
        }

        System.err.println(bookFullDto);
        // 3. Логика сохранения...
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.writeValueAsString(bookFullDto));
    }
}
